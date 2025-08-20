package dao.jdbc;

import dao.DataAccessException;
import dao.QuestionDAO;
import domain.Question;
import domain.Subject;

import java.sql.*;
import java.util.*;

public class JdbcQuestionDAO implements QuestionDAO {

    
    private static final String SQL_FIND_BY_ID =
        "SELECT id, title, content, subject_id FROM questions WHERE id = ?";
    private static final String SQL_FIND_ALL =
        "SELECT id, title, content, subject_id FROM questions ORDER BY id";
    private static final String SQL_FIND_BY_SUBJECT_ID =
        "SELECT id, title, content FROM questions WHERE subject_id = ? ORDER BY id";
    private static final String SQL_INSERT =
        "INSERT INTO questions (title, content, subject_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
        "UPDATE questions SET title = ?, content = ?, subject_id = ? WHERE id = ?";
    private static final String SQL_DELETE =
        "DELETE FROM questions WHERE id = ?";
    private static final String SQL_DELETE_BY_SUBJECT_ID =
        "DELETE FROM questions WHERE subject_id = ?";
    private static final String SQL_LOAD_SUBJECT_BY_ID =
        "SELECT id, title, info FROM subjects WHERE id = ?";

    //  DEV MESSAGES
    private static final String DEV_ERR_FIND_BY_ID =
        "findById questionId=%d failed";
    private static final String DEV_ERR_FIND_ALL =
        "findAll questions failed";
    private static final String DEV_ERR_FIND_BY_SUBJECT_ID =
        "findBySubjectId subjectId=%d failed";
    private static final String DEV_ERR_INSERT_AFFECTED_ROWS =
        "insert question affected %d rows";
    private static final String DEV_ERR_INSERT_NO_KEY =
        "insert question returned no generated key";
    private static final String DEV_ERR_INSERT_FAILED =
        "insert question failed";
    private static final String DEV_ERR_UPDATE_FAILED =
        "update questionId=%d failed";
    private static final String DEV_ERR_DELETE_FAILED =
        "delete questionId=%d failed";
    private static final String DEV_ERR_DELETE_BY_SUBJECT_FAILED =
        "delete questions by subjectId=%d failed";
    private static final String DEV_ERR_SUBJECT_NOT_FOUND =
        "Subject %d not found";
    private static final String DEV_ERR_SUBJECT_MISSING_FOR_QUESTION =
        "Subject %d missing for question %d";

    private final Connection conn;

    public JdbcQuestionDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Question> findById(int id) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                int qId = rs.getInt("id");
                String qTitle = rs.getString("title");
                String qContent = rs.getString("content");
                int subjectId = rs.getInt("subject_id");

                Subject s = loadSubjectById(subjectId);
                return Optional.of(new Question(qId, qTitle, qContent, s));
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_FIND_BY_ID, id), e);
        }
    }

    @Override
    public List<Question> findAll() {
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<RowQ> rows = new ArrayList<>();
            Set<Integer> subjectIds = new HashSet<>();
            while (rs.next()) {
                int sid = rs.getInt("subject_id");
                rows.add(new RowQ(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    sid
                ));
                subjectIds.add(sid);
            }

            Map<Integer, Subject> subjById = loadSubjectsByIds(subjectIds);

            List<Question> out = new ArrayList<>(rows.size());
            for (RowQ r : rows) {
                Subject s = subjById.get(r.subjectId);
                if (s == null) {
                    throw new DataAccessException(
                        String.format(DEV_ERR_SUBJECT_MISSING_FOR_QUESTION, r.subjectId, r.id)
                    );
                }
                out.add(new Question(r.id, r.title, r.content, s));
            }
            return out;

        } catch (SQLException e) {
            throw new DataAccessException(DEV_ERR_FIND_ALL, e);
        }
    }

    @Override
    public List<Question> findBySubjectId(int subjectId) {
        // Parent-first: load subject once, then its questions, attach SAME Subject instance
        try {
            Subject s = loadSubjectById(subjectId); // throws if subject not found
            List<Question> out = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_SUBJECT_ID)) {
                ps.setInt(1, subjectId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        out.add(new Question(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            s
                        ));
                    }
                }
            }
            return out;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_FIND_BY_SUBJECT_ID, subjectId), e);
        }
    }
    
    @Override
    public List<Question> findBySubject(Subject parent) {
        if (parent == null || parent.getSubjectId() <= 0)
            throw new IllegalArgumentException("Parent Subject must be persisted");

        final String sql = "SELECT id, title, content FROM questions WHERE subject_id = ? ORDER BY id";
        List<Question> out = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, parent.getSubjectId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Question(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        parent          // attach the SAME Subject instance
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("findBySubject failed for subjectId=" + parent.getSubjectId(), e);
        }
        return out;
    }

    @Override
    public int insert(Question q) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, q.getQuestionTitle());
            ps.setString(2, q.getQuestionContent());
            ps.setInt(3, q.getSubject().getSubjectId());
            int n = ps.executeUpdate();
            if (n != 1) throw new DataAccessException(String.format(DEV_ERR_INSERT_AFFECTED_ROWS, n));
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
            throw new DataAccessException(DEV_ERR_INSERT_NO_KEY);
        } catch (SQLException e) {
            throw new DataAccessException(DEV_ERR_INSERT_FAILED, e);
        }
    }

    @Override
    public boolean update(Question q) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, q.getQuestionTitle());
            ps.setString(2, q.getQuestionContent());
            ps.setInt(3, q.getSubject().getSubjectId());
            ps.setInt(4, q.getQuestionId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_UPDATE_FAILED, q.getQuestionId()), e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_DELETE_FAILED, id), e);
        }
    }

    @Override
    public int deleteBySubjectId(int subjectId) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_DELETE_BY_SUBJECT_ID)) {
            ps.setInt(1, subjectId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_DELETE_BY_SUBJECT_FAILED, subjectId), e);
        }
    }

    private Subject loadSubjectById(int subjectId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_LOAD_SUBJECT_BY_ID)) {
            ps.setInt(1, subjectId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new DataAccessException(String.format(DEV_ERR_SUBJECT_NOT_FOUND, subjectId));
                }
                return new Subject(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("info")
                );
            }
        }
    }

    private Map<Integer, Subject> loadSubjectsByIds(Set<Integer> ids) throws SQLException {
        Map<Integer, Subject> out = new HashMap<>();
        if (ids == null || ids.isEmpty()) return out;

        final String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
        final String sql = "SELECT id, title, info FROM subjects WHERE id IN (" + placeholders + ")";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int i = 1;
            for (Integer id : ids) ps.setInt(i++, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    out.put(id, new Subject(id, rs.getString("title"), rs.getString("info")));
                }
            }
        }
        return out;
    }

    // Temporarily holds a question’s row data (before building the final Question with a Subject)
    private static final class RowQ {
        final int id; final String title; final String content; final int subjectId;
        RowQ(int id, String title, String content, int subjectId) {
            this.id = id; this.title = title; this.content = content; this.subjectId = subjectId;
        }
    }
}
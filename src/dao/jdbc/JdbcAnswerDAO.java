package dao.jdbc;

import dao.AnswerDAO;
import dao.DataAccessException;
import domain.Answer;
import domain.Question;
import domain.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcAnswerDAO implements AnswerDAO {


    private static final String SQL_FIND_BY_ID =
        "SELECT id, content, valid, question_id FROM answers WHERE id = ?";
    private static final String SQL_FIND_PARENT_QUESTION =
        "SELECT id, title, content, subject_id FROM questions WHERE id = ?";
    private static final String SQL_FIND_BY_QUESTION_ID =
        "SELECT id, content, valid FROM answers WHERE question_id = ? ORDER BY id";
    private static final String SQL_INSERT =
        "INSERT INTO answers (content, valid, question_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
        "UPDATE answers SET content = ?, valid = ?, question_id = ? WHERE id = ?";
    private static final String SQL_DELETE =
        "DELETE FROM answers WHERE id = ?";
    private static final String SQL_DELETE_BY_QUESTION_ID =
        "DELETE FROM answers WHERE question_id = ?";
    private static final String SQL_DELETE_BY_SUBJECT_ID =
        "DELETE FROM answers WHERE question_id IN (SELECT id FROM questions WHERE subject_id = ?)";
    private static final String SQL_LOAD_SUBJECT_BY_ID =
        "SELECT id, title, info FROM subjects WHERE id = ?";
    private static final String SQL_LOAD_QUESTION_WITH_SUBJECT =
        "SELECT id, title, content, subject_id FROM questions WHERE id = ?";

    //DEV-FACING MESSAGES 
    private static final String DEV_ERR_FIND_BY_ID =
        "findById answerId=%d failed";
    private static final String DEV_ERR_LOAD_PARENT_QUESTION =
        "load parent questionId=%d failed";
    private static final String DEV_ERR_FIND_BY_QUESTION_ID =
        "findByQuestionId questionId=%d failed";
    private static final String DEV_ERR_INSERT_ROWS =
        "insert answer affected %d rows";
    private static final String DEV_ERR_INSERT_NO_KEY =
        "insert answer returned no generated key";
    private static final String DEV_ERR_INSERT_FAILED =
        "insert answer failed";
    private static final String DEV_ERR_UPDATE_FAILED =
        "update answerId=%d failed";
    private static final String DEV_ERR_DELETE_FAILED =
        "delete answerId=%d failed";
    private static final String DEV_ERR_DELETE_BY_QUESTION_FAILED =
        "delete answers by questionId=%d failed";
    private static final String DEV_ERR_DELETE_BY_SUBJECT_FAILED =
        "delete answers by subjectId=%d failed";
    private static final String DEV_ERR_SUBJECT_NOT_FOUND =
        "Subject %d not found";
    private static final String DEV_ERR_QUESTION_NOT_FOUND =
        "Question %d not found";

    private final Connection conn;

    public JdbcAnswerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Answer> findById(int id) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                int aId = rs.getInt("id");
                String content = rs.getString("content");
                boolean valid = rs.getBoolean("valid");
                int qid = rs.getInt("question_id");

                Question q = loadQuestionWithSubject(qid);
                return Optional.of(new Answer(aId, content, valid, q));
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_FIND_BY_ID, id), e);
        }
    }

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        // 1) Load parent Question (+ Subject)
        Question parent;
        try (PreparedStatement psQ = conn.prepareStatement(SQL_FIND_PARENT_QUESTION)) {
            psQ.setInt(1, questionId);
            try (ResultSet rsQ = psQ.executeQuery()) {
                if (!rsQ.next()) {
                    return new ArrayList<>(); // no such question
                }
                int sid = rsQ.getInt("subject_id");
                Subject s = loadSubjectById(sid);
                parent = new Question(
                    rsQ.getInt("id"),
                    rsQ.getString("title"),
                    rsQ.getString("content"),
                    s
                );
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_LOAD_PARENT_QUESTION, questionId), e);
        }

        // 2) Load answers, attach SAME parent
        List<Answer> out = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_QUESTION_ID)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Answer(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getBoolean("valid"),
                        parent
                    ));
                }
            }
            return out;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_FIND_BY_QUESTION_ID, questionId), e);
        }
    }

    @Override
    public int insert(Answer a) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getAnswerContent());
            ps.setBoolean(2, a.isCorrect());
            ps.setInt(3, a.getQuestion().getQuestionId());
            int n = ps.executeUpdate();
            if (n != 1) throw new DataAccessException(String.format(DEV_ERR_INSERT_ROWS, n));
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
            throw new DataAccessException(DEV_ERR_INSERT_NO_KEY);
        } catch (SQLException e) {
            throw new DataAccessException(DEV_ERR_INSERT_FAILED, e);
        }
    }

    @Override
    public boolean update(Answer a) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, a.getAnswerContent());
            ps.setBoolean(2, a.isCorrect());
            ps.setInt(3, a.getQuestion().getQuestionId());
            ps.setInt(4, a.getAnswerId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_UPDATE_FAILED, a.getAnswerId()), e);
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
    public int deleteByQuestionId(int questionId) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_DELETE_BY_QUESTION_ID)) {
            ps.setInt(1, questionId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(String.format(DEV_ERR_DELETE_BY_QUESTION_FAILED, questionId), e);
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

    // ---------- local helpers (no DAO-to-DAO deps) ----------

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

    private Question loadQuestionWithSubject(int questionId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_LOAD_QUESTION_WITH_SUBJECT)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new DataAccessException(String.format(DEV_ERR_QUESTION_NOT_FOUND, questionId));
                }
                int sid = rs.getInt("subject_id");
                Subject s = loadSubjectById(sid);
                return new Question(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    s
                );
            }
        }
    }
}

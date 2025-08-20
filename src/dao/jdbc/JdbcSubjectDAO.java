package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.DataAccessException;
import dao.SubjectDAO;
import domain.Subject;

public class JdbcSubjectDAO implements SubjectDAO {

   
    private static final String SQL_FIND_BY_ID =
        "SELECT id, title, info FROM subjects WHERE id = ?";
    private static final String SQL_FIND_ALL =
        "SELECT id, title, info FROM subjects ORDER BY id";
    private static final String SQL_INSERT =
        "INSERT INTO subjects (title, info) VALUES (?, ?)";
    private static final String SQL_UPDATE =
        "UPDATE subjects SET title = ?, info = ? WHERE id = ?";
    private static final String SQL_DELETE =
        "DELETE FROM subjects WHERE id = ?";


    private static final String ERR_FIND_BY_ID =
        "Suchen des Themas mit id=%d fehlgeschlagen";
    private static final String ERR_FIND_ALL =
        "Laden aller Themen fehlgeschlagen";
    private static final String ERR_INSERT_ROWS =
        "Einfügen des Themas betraf %d Zeilen";
    private static final String ERR_INSERT_NO_KEY =
        "Einfügen des Themas hat keinen generierten Schlüssel zurückgegeben";
    private static final String ERR_INSERT_FAILED =
        "Einfügen des Themas fehlgeschlagen";
    private static final String ERR_UPDATE =
        "Aktualisieren des Themas mit id=%d fehlgeschlagen";
    private static final String ERR_DELETE =
        "Löschen des Themas mit id=%d fehlgeschlagen";

    private final Connection conn;

    public JdbcSubjectDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Subject> findById(int id) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException(String.format(ERR_FIND_BY_ID, id), e);
        }
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
            return list;
        } catch (SQLException e) {
            throw new DataAccessException(ERR_FIND_ALL, e);
        }
    }

    @Override
    public int insert(Subject subject) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, subject.getSubjectTitle());
            ps.setString(2, subject.getSubjectInfo());
            int updated = ps.executeUpdate();
            if (updated != 1) throw new DataAccessException(String.format(ERR_INSERT_ROWS, updated));
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
            throw new DataAccessException(ERR_INSERT_NO_KEY);
        } catch (SQLException e) {
            throw new DataAccessException(ERR_INSERT_FAILED, e);
        }
    }

    @Override
    public boolean update(Subject subject) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, subject.getSubjectTitle());
            ps.setString(2, subject.getSubjectInfo());
            ps.setInt(3, subject.getSubjectId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(ERR_UPDATE, subject.getSubjectId()), e);
        }
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException(String.format(ERR_DELETE, id), e);
        }
    }

    private Subject mapRow(ResultSet rs) throws SQLException {
        return new Subject(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("info")
        );
    }
}
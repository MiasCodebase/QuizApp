package dao.jdbc;

import dao.*;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDAOFactory implements DAOFactory {
    private final Connection conn;

    // Lazily allocate DAO instances so they all share the same Connection.
    private SubjectDAO subjectDAO;
    private QuestionDAO questionDAO;
    private AnswerDAO answerDAO;

    public JdbcDAOFactory(Connection conn) {
        this.conn = conn;
    }

    @Override
    public SubjectDAO subjects() {
        if (subjectDAO == null) subjectDAO = new JdbcSubjectDAO(conn);
        return subjectDAO;
    }

    @Override
    public QuestionDAO questions() {
        if (questionDAO == null) questionDAO = new JdbcQuestionDAO(conn);
        return questionDAO;
    }

    @Override
    public AnswerDAO answers() {
        if (answerDAO == null) answerDAO = new JdbcAnswerDAO(conn);
        return answerDAO;
    }

    @Override
    public void begin() {
        try {
            if (conn.getAutoCommit()) conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to begin transaction", e);
        }
    }

    @Override
    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new DataAccessException("Commit failed", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    @Override
    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            // prefer not to mask the original error; still wrap for consistency
            throw new DataAccessException("Rollback failed", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    @Override
    public void close() {
        try { conn.close(); } catch (SQLException ignored) {}
    }
}

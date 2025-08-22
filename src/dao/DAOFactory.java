package dao;

public interface DAOFactory extends AutoCloseable {
    SubjectDAO subjects();
    QuestionDAO questions();
    AnswerDAO answers();

    /** Start a transaction (sets autoCommit=false). */
    void begin();

    /** Commit and restore autoCommit=true. */
    void commit();

    /** Roll back and restore autoCommit=true. */
    void rollback();

    /** Always close the underlying Connection. */
    @Override
    void close();
}

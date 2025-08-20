package dao;

/** Unchecked wrapper for SQL / I/O failures in the data access layer. */
public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataAccessException(String message) {
        super(message);
    }
}
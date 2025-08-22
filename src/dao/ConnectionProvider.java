package dao;

import java.sql.Connection;
import java.sql.SQLException;

/** Abstraction so you can swap DBs easily (MariaDB, test DB, etc.). */
public interface ConnectionProvider {
    Connection getConnection() throws SQLException;
}
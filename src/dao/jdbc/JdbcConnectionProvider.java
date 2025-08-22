package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ConnectionProvider;

public class JdbcConnectionProvider implements ConnectionProvider {
	
	public static final String URL = "jdbc:mysql://localhost:3306/myswing";
	public static final String USER = "root";
	public static final String PASSWORD = "";

	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	

}

package sample;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private DBConnect() {

	}

	public static DBConnect getInstance() {
		return new DBConnect();
	}

	public Connection getConnection() {
		String connect_string = "jdbc:sqlite:data.db";

		Connection connection = null;
		try {

			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(connect_string);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}

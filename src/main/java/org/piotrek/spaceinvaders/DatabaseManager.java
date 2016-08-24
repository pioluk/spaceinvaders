package org.piotrek.spaceinvaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	private static void prepareDatabase(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS highscores (" +
			"  name TEXT NOT NULL," +
			"  score INT NOT NULL," +
			"  finishTime TEXT NOT NULL," +
			"  gameDuration INT NOT NULL" +
			")";

		statement.executeUpdate(sql);
		statement.close();
	}

	public static Connection getConnection(String path) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + path);
		prepareDatabase(connection);

		return connection;
	}

}

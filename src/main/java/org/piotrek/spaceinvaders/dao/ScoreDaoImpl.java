package org.piotrek.spaceinvaders.dao;

import org.piotrek.spaceinvaders.model.Score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaoImpl implements ScoreDao {

	private Connection connection;

	public ScoreDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public List<Score> findAll() throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM highscores ORDER BY score DESC, gameDuration";

		ResultSet resultSet = statement.executeQuery(sql);

		List<Score> scores = new ArrayList<>();

		while (resultSet.next()) {
			Score score = new Score();
			score.setName(resultSet.getString("name"));
			score.setScore(resultSet.getInt("score"));
			score.setFinishTime(resultSet.getString("finishTime"));
			score.setGameDuration(resultSet.getLong("gameDuration"));

			scores.add(score);
		}

		resultSet.close();
		statement.close();

		return scores;
	}

	public void save(Score score) throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO highscores VALUES (" +
			"\"" + score.getName() + "\"," +
			score.getScore() + "," +
			"\"" + score.getFinishTime() + "\"," +
			score.getGameDuration() +
			")";

		System.out.println(sql);

		statement.executeUpdate(sql);
		statement.close();
	}

}

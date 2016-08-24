package org.piotrek.spaceinvaders.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.piotrek.spaceinvaders.model.Score;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DaoTest {

	DataSource mockDataSource;
	Connection mockConn;
	Statement mockStatement;
	ResultSet mockResultSet;

	@Before
	public void init() {
		mockDataSource = mock(DataSource.class);
		mockConn = mock(Connection.class);
		mockStatement = mock(Statement.class);
		mockResultSet = mock(ResultSet.class);
	}

	@Test
	public void getAllTest() throws SQLException {
		when(mockConn.createStatement()).thenReturn(mockStatement);
		when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

		Mockito.when(mockResultSet.getString("name")).thenReturn("Artur");
		Mockito.when(mockResultSet.getInt("score")).thenReturn(999);
		Mockito.when(mockResultSet.getString("finishTime")).thenReturn("2016-08-08 16:08:23");
		Mockito.when(mockResultSet.getLong("gameDuration")).thenReturn(21L);

		Score score = new Score();
		score.name = "Artur";
		score.finishTime = "2016-08-08 16:08:23";
		score.gameDuration = 21;
		score.score = 999;

		List<Score> list = new ArrayList<>();
		list.add(score);

		ScoreDao dao = new ScoreDaoImpl(mockConn);
		List<Score> scoreList = dao.findAll();

		for (int i = 0; i < scoreList.size(); i++) {
			assertEquals(scoreList.get(i).name, list.get(i).name);
			assertEquals(scoreList.get(i).score, list.get(i).score);
			assertEquals(scoreList.get(i).gameDuration, list.get(i).gameDuration);
			assertEquals(scoreList.get(i).finishTime, list.get(i).finishTime);
		}
	}

	@Test
	public void saveTest() throws SQLException, ClassNotFoundException {
		when(mockConn.createStatement()).thenReturn(mockStatement);
		when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

		ScoreDao dao = new ScoreDaoImpl(mockConn);

		Score score = new Score();
		score.name = "Artur";
		score.finishTime = "2016-08-08 16:08:23";
		score.gameDuration = 21;
		score.score = 999;

		dao.save(score);

		List<Score> scores = new ArrayList<>();

		while (mockResultSet.next()) {
			Score scoreTemp = new Score();
			scoreTemp.setName(mockResultSet.getString("name"));
			scoreTemp.setScore(mockResultSet.getInt("score"));
			scoreTemp.setFinishTime(mockResultSet.getString("finishTime"));
			scoreTemp.setGameDuration(mockResultSet.getLong("gameDuration"));

			scores.add(score);
		}

		assertThat(score.getName(), equalTo(scores.get(0).getName()));
		assertThat(score.getFinishTime(), equalTo(scores.get(0).getFinishTime()));
		assertThat(score.getGameDuration(), equalTo(scores.get(0).getGameDuration()));
		assertThat(score.getScore(), equalTo(scores.get(0).getScore()));
	}

}


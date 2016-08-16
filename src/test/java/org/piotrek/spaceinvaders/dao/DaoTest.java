package org.piotrek.spaceinvaders.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.piotrek.spaceinvaders.model.Score;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Arie on 13.08.2016.
 */
public class DaoTest {

    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn = Mockito.mock(Connection.class);
    @Mock
    Statement mockStatement = Mockito.mock(Statement.class);
    @Mock
    ResultSet mockResultSet = mock(ResultSet.class);


    @Before
    public void setUp() throws SQLException {



    }

    @Test
    public void getAllTest() throws SQLException {


        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConn.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);//, Boolean.TRUE, Boolean.FALSE);

        Mockito.when(mockResultSet.getString("name")).thenReturn("Artur");
        Mockito.when(mockResultSet.getInt("score")).thenReturn(999);
        Mockito.when(mockResultSet.getString("finishTime")).thenReturn("2016-08-08 16:08:23");
        Mockito.when(mockResultSet.getLong("gameDuration")).thenReturn(21L);


        ScoreDao dao = new ScoreDaoImpl(mockConn);

        Score score = new Score();
        score.name = "Artur";
        score.finishTime = "2016-08-08 16:08:23";
        score.gameDuration = 21;
        score.score = 999;

        List<Score> list = new ArrayList<>();
        list.add(score);

        List<Score> scoreList = scoreList = dao.findAll();

        for(int i =0; i<scoreList.size(); i++)
        {
            assertEquals(scoreList.get(i).name,list.get(i).name);
            assertEquals(scoreList.get(i).score,list.get(i).score);
            assertEquals(scoreList.get(i).gameDuration,list.get(i).gameDuration);
            assertEquals(scoreList.get(i).finishTime,list.get(i).finishTime);
        }
    }

    @Test
    public void saveTest() throws SQLException {

        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConn.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        ScoreDao dao = new ScoreDaoImpl(mockConn);

        Score score = new Score();
        score.name = "Artur";
        score.finishTime = "2016-08-08 16:08:23";
        score.gameDuration = 21;
        score.score = 999;

        dao.save(score);
        //TODO how i can test database?
    }




}

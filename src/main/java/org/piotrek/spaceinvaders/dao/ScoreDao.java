package org.piotrek.spaceinvaders.dao;

import org.piotrek.spaceinvaders.model.Score;

import java.sql.SQLException;
import java.util.List;

public interface ScoreDao {
	List<Score> findAll() throws SQLException;
	void save(Score score) throws SQLException;
}

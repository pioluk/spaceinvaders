package org.piotrek.spaceinvaders.controller;

import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.model.Board;
import org.piotrek.spaceinvaders.model.BoardFactory;
import org.piotrek.spaceinvaders.model.Game;
import org.piotrek.spaceinvaders.model.Invader;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


public class GameControllerTest {

	private Game game;
	private GameController gameController;

	@Before
	public void initialize() {
		game = new Game();
		gameController = new GameController(game);
	}

	@Test
	public void shouldStartGame() throws Exception {
		gameController.startGame();
		assertThat(game.isStarted()).isTrue();
	}

	@Test
	public void shouldCorrectlyTogglePause() throws Exception {
		gameController.togglePaused();
		assertThat(game.isPaused()).isTrue();

		gameController.togglePaused();
		assertThat(game.isPaused()).isFalse();

		gameController.togglePaused();
		assertThat(game.isPaused()).isTrue();
	}

	@Test
	public void shouldStartWithZeroScore() {
		assertThat(game.getScore()).isZero();
	}

	@Test
	public void shouldAddScore() {
		int score = 25;
		gameController.addToScore(score);
		assertThat(game.getScore()).isEqualTo(score);
	}

	@Test
	public void shouldComputeCorrectScore() {
		Board board = BoardFactory.create(game.getLevel());
		BoardController boardController = new BoardController(board);

		ArrayList<Invader> invanders = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			invanders.add(new Invader(1, 1));
		}

		invanders.get(1).decreaseHP();
		invanders.get(6).decreaseHP();
		invanders.get(7).decreaseHP();

		board.setInvaders(invanders);

		boardController.forEachDeadInvader(p -> gameController.addToScore(p));

		assertThat(game.getScore(), equalTo(3));
	}

}

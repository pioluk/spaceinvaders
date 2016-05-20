package org.piotrek.spaceinvaders.controller;

import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.model.Game;

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

}
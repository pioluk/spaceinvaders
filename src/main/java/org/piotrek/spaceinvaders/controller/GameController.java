package org.piotrek.spaceinvaders.controller;

import org.piotrek.spaceinvaders.model.Game;

import javax.inject.Inject;

public class GameController {

	private Game game;

	@Inject
	public GameController(Game game) {
		this.game = game;
	}

	public void startGame() {
		game.setStarted(true);
	}

	public void togglePaused() {
		game.setPaused(!game.isPaused());
	}
}

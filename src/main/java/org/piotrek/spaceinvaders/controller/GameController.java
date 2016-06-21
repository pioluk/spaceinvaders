package org.piotrek.spaceinvaders.controller;

import org.piotrek.spaceinvaders.model.Game;

public class GameController {

	private Game game;

	public GameController(Game game) {
		this.game = game;
	}

	public void startGame() {
		game.setStarted(true);
	}

	public void togglePaused() {
		game.setPaused(!game.isPaused());
	}

	public void addToScore(int points) {
		game.setScore(game.getScore() + points);
	}
}

package org.piotrek.spaceinvaders.controller;

import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Player;

public class PlayerController {
	private static final double SINGLE_MOVE_DISTANCE = 10.0;

	private Player player;

	public PlayerController(Player player) {
		this.player = player;
	}

	public void movePlayerLeft() {
		double x = Math.max(0, player.getX() - SINGLE_MOVE_DISTANCE);
		player.setX(x);
	}

	public void movePlayerRight() {
		double x = Math.min(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH, player.getX() + SINGLE_MOVE_DISTANCE);
		player.setX(x);
	}

}

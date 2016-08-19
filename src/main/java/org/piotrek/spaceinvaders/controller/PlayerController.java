package org.piotrek.spaceinvaders.controller;

import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Player;

public class PlayerController {

	private Player player;

	public PlayerController(Player player) {
		this.player = player;
	}

	public void moveLeft() {
		double x = Math.max(0, player.getX() - Config.SINGLE_MOVE_DISTANCE_FOR_PLAYER);
		player.setX(x);
	}

	public void moveRight() {
		double x = Math.min(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH, player.getX() + Config.SINGLE_MOVE_DISTANCE_FOR_PLAYER);
		player.setX(x);
	}

}

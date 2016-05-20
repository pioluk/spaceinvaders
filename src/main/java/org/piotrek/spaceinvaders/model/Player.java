package org.piotrek.spaceinvaders.model;


import org.piotrek.spaceinvaders.Config;

public class Player extends Sprite {

	private static final double MARGIN_BOTTOM = 65.0;

	public Player() {
		this.x = Config.WINDOW_WIDTH / 2.0 - Config.PLAYER_WIDTH / 2.0;
		this.y = Config.WINDOW_HEIGHT - MARGIN_BOTTOM;
	}

}

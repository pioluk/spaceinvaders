package org.piotrek.spaceinvaders.model;


import org.piotrek.spaceinvaders.Config;

public class Player extends Sprite {

	public Player() {
		this.x = Config.WINDOW_WIDTH / 2.0 - Config.PLAYER_WIDTH / 2.0;
		this.y = Config.WINDOW_HEIGHT - Config.PLAYER_MARGIN_BOTTOM;
	}

}

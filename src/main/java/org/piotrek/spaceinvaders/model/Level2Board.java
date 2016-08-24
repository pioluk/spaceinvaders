package org.piotrek.spaceinvaders.model;

import org.piotrek.spaceinvaders.Config;

import java.util.ArrayList;

public class Level2Board extends Board {

	public static final int ROWS = 4;
	public static final int COLUMNS = 7;

	public Level2Board() {
		player = new Player();
		invaders = new ArrayList<>();

		double y = Config.INVADER_MARGIN;

		for (int i = 0; i < ROWS - 1; ++i) {
			double x = Config.WINDOW_WIDTH / 2.0 - COLUMNS / 2.0 * (Config.INVADER_WIDTH + Config.INVADER_MARGIN);

			for (int j = 0; j < COLUMNS; ++j) {
				Invader invader = InvaderFactory.create(InvaderType.MEDIUM);
				invader.setX(x);
				invader.setY(y);
				invaders.add(invader);

				x += Config.INVADER_WIDTH + Config.INVADER_MARGIN;
			}

			y += Config.INVADER_HEIGHT + Config.INVADER_MARGIN;
		}

		double x = Config.WINDOW_WIDTH / 2.0 - COLUMNS / 2.0 * (Config.INVADER_WIDTH + Config.INVADER_MARGIN);

		for (int j = 0; j < COLUMNS; ++j) {
			Invader invader = InvaderFactory.create(InvaderType.EASY);
			invader.setX(x);
			invader.setY(y);
			invaders.add(invader);

			x += Config.INVADER_WIDTH + Config.INVADER_MARGIN;
		}
	}

}

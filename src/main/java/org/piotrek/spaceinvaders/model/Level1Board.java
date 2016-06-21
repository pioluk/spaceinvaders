package org.piotrek.spaceinvaders.model;

import org.piotrek.spaceinvaders.Config;

import java.util.ArrayList;

public class Level1Board extends Board {

	private static final int ROWS = 3;
	private static final int COLUMNS = 6;

	public Level1Board() {
		player = new Player();
		invaders = new ArrayList<>();

		double y = Config.INVADER_MARGIN;

		for (int i = 0; i < ROWS; ++i) {
			double x = Config.WINDOW_WIDTH / 2.0 - COLUMNS / 2.0 * (Config.INVADER_WIDTH + Config.INVADER_MARGIN);

			for (int j = 0; j < COLUMNS; ++j) {
				Invader invader = InvaderFactory.create(InvaderType.EASY);
				invader.setX(x);
				invader.setY(y);
				invaders.add(invader);

				x += Config.INVADER_WIDTH + Config.INVADER_MARGIN;
			}

			y += Config.INVADER_HEIGHT + Config.INVADER_MARGIN;
		}
	}

}

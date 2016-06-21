package org.piotrek.spaceinvaders.model;

public class BoardFactory {

	public static Board create(int level) {
		switch (level) {
			case 1:
				return new Level1Board();

			case 2:
				return new Level2Board();

			default:
				throw new IllegalArgumentException();
		}
	}

}

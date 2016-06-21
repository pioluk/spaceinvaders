package org.piotrek.spaceinvaders.model;

public class BoardFactory {

	public static Board create(int level) {
		switch (level) {
			case 1:
				return new Level1Board();

			default:
				throw new IllegalArgumentException();
		}
	}

}

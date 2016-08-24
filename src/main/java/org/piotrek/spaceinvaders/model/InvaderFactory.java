package org.piotrek.spaceinvaders.model;

public class InvaderFactory {

	public static Invader create(InvaderType invaderType) {
		switch (invaderType) {
			case EASY:
				return new Invader(1, 5);

			case MEDIUM:
			case HARD:
				return new Invader(3, 10);

			default:
				throw new IllegalArgumentException();
		}
	}

}

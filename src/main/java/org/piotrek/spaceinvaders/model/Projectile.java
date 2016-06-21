package org.piotrek.spaceinvaders.model;

public class Projectile extends Sprite {

	private static final double ADVANCE_STEP = 5.0;

	public void advance() {
		y -= ADVANCE_STEP;
	}

}

package org.piotrek.spaceinvaders.model;

import org.piotrek.spaceinvaders.Config;

public class Projectile extends Sprite {

	public void advance() {
		y -= Config.PROJECTILE_ADVANCE_STEP;
	}

}

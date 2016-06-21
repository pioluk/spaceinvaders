package org.piotrek.spaceinvaders.model;

public class AbstractInvader extends Sprite {

	protected int hp;

	public void decreaseHP() {
		hp = Math.max(hp - 1, 0);
	}

	public boolean isDead() {
		return hp == 0;
	}

}

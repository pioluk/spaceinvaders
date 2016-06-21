package org.piotrek.spaceinvaders.model;

public class Invader extends Sprite {

	protected int hp;
	protected int points;

	public Invader(int hp, int points) {
		this.hp = hp;
		this.points = points;
	}

	public void decreaseHP() {
		hp = Math.max(hp - 1, 0);
	}

	public boolean isDead() {
		return hp == 0;
	}

	public int getPoints() {
		return points;
	}

}

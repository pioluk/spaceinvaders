package org.piotrek.spaceinvaders.model;

public class State {

	private int score;
	private double playerX;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getPlayerX() {
		return playerX;
	}

	public void setPlayerX(double playerX) {
		this.playerX = playerX;
	}

	public void playerMoveLeft() {
		playerX -= 10d;
	}

	public void playerMoveRight() {
		playerX += 10d;
	}
}

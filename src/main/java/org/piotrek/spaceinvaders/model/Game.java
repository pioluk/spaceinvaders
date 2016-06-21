package org.piotrek.spaceinvaders.model;

public class Game {

	private boolean started;
	private boolean paused;
	private boolean won;
	private boolean over;
	private boolean changingLevel;
	private int score;
	private int level;

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Game() {
		started = false;
		paused = false;
		won = false;
		over = false;
		changingLevel = false;

		score = 0;
		level = 1;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void increaseLevel() {
		++level;
	}

	public boolean isChangingLevel() {
		return changingLevel;
	}

	public void setChangingLevel(boolean changingLevel) {
		this.changingLevel = changingLevel;
	}
}

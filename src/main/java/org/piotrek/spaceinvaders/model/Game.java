package org.piotrek.spaceinvaders.model;

public class Game {

	private boolean started;
	private boolean paused;

	public Game() {
		started = false;
		paused = false;
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
}

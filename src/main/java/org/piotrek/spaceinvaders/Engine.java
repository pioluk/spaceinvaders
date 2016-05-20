package org.piotrek.spaceinvaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.piotrek.spaceinvaders.controller.GameController;
import org.piotrek.spaceinvaders.controller.PlayerController;
import org.piotrek.spaceinvaders.model.Game;
import org.piotrek.spaceinvaders.model.Player;
import org.piotrek.spaceinvaders.view.*;

public class Engine {

	private Game game = new Game();
	private Player player = new Player();

	private GameController gameController = new GameController(game);
	private PlayerController playerController = new PlayerController(player);

	private View backgroundView = new BackgroundView();
	private View welcomeView = new WelcomeView();
	private View playerView = new PlayerView(player);
	private View pauseView = new PauseView();

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}

	public View getBackgroundView() {
		return backgroundView;
	}

	public void setBackgroundView(View backgroundView) {
		this.backgroundView = backgroundView;
	}

	public View getWelcomeView() {
		return welcomeView;
	}

	public void setWelcomeView(View welcomeView) {
		this.welcomeView = welcomeView;
	}

	public View getPlayerView() {
		return playerView;
	}

	public void setPlayerView(View playerView) {
		this.playerView = playerView;
	}

	public View getPauseView() {
		return pauseView;
	}

	public void setPauseView(View pauseView) {
		this.pauseView = pauseView;
	}

	public void update(long time) {
		// noop
	}

	public void render(GraphicsContext graphicsContext) {
		backgroundView.render(graphicsContext);

		if (game.isStarted()) {
			playerView.render(graphicsContext);

			if (game.isPaused()) {
				pauseView.render(graphicsContext);
			}
		}
		else {
			welcomeView.render(graphicsContext);
		}
	}

	public void handleKeyPressed(KeyEvent event) {
		KeyCode keyCode = event.getCode();

		switch (keyCode) {
			case SPACE:
				startGame();
				break;

			case Q:
				quitGame();
				break;

			case P:
				togglePaused();
				break;

			case A:
			case LEFT:
				movePlayerLeft();
				break;

			case D:
			case RIGHT:
				movePlayerRight();
				break;
		}
	}

	public void handleKeyReleased(KeyEvent event) {
		// noop
	}

	public void startGame() {
		gameController.startGame();
	}

	public void togglePaused() {
		gameController.togglePaused();
	}

	public void movePlayerLeft() {
		if (game.isStarted() && !game.isPaused()) {
			playerController.movePlayerLeft();
		}
	}

	public void movePlayerRight() {
		if (game.isStarted() && !game.isPaused()) {
			playerController.movePlayerRight();
		}
	}

	private void quitGame() {
		System.exit(0);
	}

}

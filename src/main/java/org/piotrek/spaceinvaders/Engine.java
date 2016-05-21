package org.piotrek.spaceinvaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.piotrek.spaceinvaders.controller.GameController;
import org.piotrek.spaceinvaders.controller.PlayerController;
import org.piotrek.spaceinvaders.model.Game;
import org.piotrek.spaceinvaders.model.Player;
import org.piotrek.spaceinvaders.view.*;

import javax.inject.Inject;

public class Engine {

	// @Inject
	Game game;
	// @Inject
	Player player;

	// @Inject
	GameController gameController;
	// @Inject
	PlayerController playerController;

	// @Inject
	BackgroundView backgroundView;
	// @Inject
	WelcomeView welcomeView;
	// @Inject
	PlayerView playerView;
	// @Inject
	PauseView pauseView;

	@Inject
	public Engine(Game game,
	              Player player,
	              GameController gameController,
	              PlayerController playerController,
	              BackgroundView backgroundView,
	              WelcomeView welcomeView,
	              PlayerView playerView,
	              PauseView pauseView) {
		this.game = game;
		this.player = player;
		this.gameController = gameController;
		this.playerController = playerController;
		this.backgroundView = backgroundView;
		this.welcomeView = welcomeView;
		this.playerView = playerView;
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

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

	public void update(long time) {
		// noop
	}

	public void render(GraphicsContext graphicsContext) {
		backgroundView.render(graphicsContext);

		if (game.isStarted()) {
			playerView.render(graphicsContext);
		}
		else {
			welcomeView.render(graphicsContext);
		}
	}

	public void handleKeyPressed(KeyEvent event) {
		KeyCode keyCode = event.getCode();

		switch (keyCode) {
			case SPACE:
				gameController.startGame();
				break;

			case Q:
				quitGame();
				break;

			case P:
				gameController.togglePaused();
				break;

			case A:
			case LEFT:
				playerController.movePlayerLeft();
				break;

			case D:
			case RIGHT:
				playerController.movePlayerRight();
				break;
		}
	}

	public void handleKeyReleased(KeyEvent event) {
		//
	}

	private void quitGame() {
		System.exit(0);
	}
}

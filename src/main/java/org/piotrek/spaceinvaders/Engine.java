package org.piotrek.spaceinvaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.piotrek.spaceinvaders.controller.BoardController;
import org.piotrek.spaceinvaders.controller.GameController;
import org.piotrek.spaceinvaders.model.*;
import org.piotrek.spaceinvaders.view.*;

public class Engine {

	private Game game = new Game();
	private Board board = BoardFactory.create(1);

	private GameController gameController = new GameController(game);
	private BoardController boardController = new BoardController(board);

	private View backgroundView = new BackgroundView();
	private View welcomeView = new WelcomeView();
	private View pauseView = new PauseView();
	private View boardView = new BoardView(board);

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public void setBoardController(BoardController boardController) {
		this.boardController = boardController;
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

	public View getPauseView() {
		return pauseView;
	}

	public void setPauseView(View pauseView) {
		this.pauseView = pauseView;
	}

	public void update(long time) {
		boardController.advanceProjectiles();
		boardController.detectCollisions();
		boardController.removeDeadInvaders();
	}

	public void render(GraphicsContext graphicsContext) {
		backgroundView.render(graphicsContext);

		if (game.isStarted()) {
			boardView.render(graphicsContext);

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
				handleSpacePressed();
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

	private void handleSpacePressed() {
		if (!game.isStarted()) {
			startGame();
		}
		else {
			fireProjectile();
		}
	}

	private void fireProjectile() {
		boardController.fireProjectile();
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
			boardController.movePlayerLeft();
		}
	}

	public void movePlayerRight() {
		if (game.isStarted() && !game.isPaused()) {
			boardController.movePlayerRight();
		}
	}

	private void quitGame() {
		System.exit(0);
	}

}

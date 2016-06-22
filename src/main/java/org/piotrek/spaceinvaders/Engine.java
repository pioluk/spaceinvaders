package org.piotrek.spaceinvaders;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.piotrek.spaceinvaders.controller.BoardController;
import org.piotrek.spaceinvaders.controller.GameController;
import org.piotrek.spaceinvaders.dao.ScoreDaoImpl;
import org.piotrek.spaceinvaders.model.*;
import org.piotrek.spaceinvaders.view.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Engine {

	private static final String DATABASE_PATH = "spaceinvaders.db";
	private Instant startGameTime;
	private Instant endGameTime;

	private Game game = new Game();
	private Board board = BoardFactory.create(game.getLevel());

	private GameController gameController = new GameController(game);
	private BoardController boardController = new BoardController(board);

	private View backgroundView = new BackgroundView();
	private View welcomeView = new WelcomeView();
	private View pauseView = new PauseView();
	private BoardView boardView = new BoardView(board);
	private ScoreView scoreView = new ScoreView();
	private ChangingLevelView changingLevelView = new ChangingLevelView();
	private View gameWonView = new GameWonView();
	private View gameOverView = new GameOverView();
	private LeaderboardView leaderboardView = new LeaderboardView();

	private boolean showingLeaderboard = false;

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
		if (game.isStarted() && !game.isChangingLevel() && !game.isWon() && !game.isOver() && !game.isPaused()) {
			scoreView.setScore(game.getScore());

			boardController.advanceProjectiles();
			boardController.detectCollisions();
			boardController.forEachDeadInvader(points -> gameController.addToScore(points));
			boardController.removeDeadInvaders();
			boardController.moveInvaders(time, game.getLevel());
			checkIfLevelCompleted();
			checkIfGameOver();
		}
	}

	private void checkIfGameOver() {
		List<Invader> invaders = board.getInvaders();
		Player player = board.getPlayer();

		for (Invader invader : invaders) {
			if (invader.getY() + Config.INVADER_HEIGHT >= player.getY()) {
				gameOver();
				break;
			}
		}
	}

	private void gameOver() {
		game.setOver(true);
		finishGame();
	}

	private void checkIfLevelCompleted() {
		if (board.getInvaders().size() > 0) {
			return;
		}

		if (game.getLevel() == Config.MAX_LEVEL) {
			gameWon();
			return;
		}

		game.setChangingLevel(true);

		game.increaseLevel();
		changingLevelView.setLevel(game.getLevel());

		Timer startNewLevel = new Timer();
		startNewLevel.schedule(new TimerTask() {
			@Override
			public void run() {
				completeChangingLevels();
			}
		}, 2500);
	}

	private void gameWon() {
		game.setWon(true);
		finishGame();
	}

	public void completeChangingLevels() {
		game.setChangingLevel(false);
		changeLevel();
	}

	private void changeLevel() {
		board = BoardFactory.create(game.getLevel());
		boardController.setBoard(board);
		boardView.setBoard(board);
	}

	public void render(GraphicsContext graphicsContext) {
		backgroundView.render(graphicsContext);

		if (showingLeaderboard) {
			leaderboardView.render(graphicsContext);
		}
		else if (game.isChangingLevel()) {
			changingLevelView.render(graphicsContext);
		}
		else if (game.isOver()) {
			gameOverView.render(graphicsContext);
		}
		else if (game.isWon()) {
			gameWonView.render(graphicsContext);
		}
		else if (game.isStarted()) {
			boardView.render(graphicsContext);
			scoreView.render(graphicsContext);

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

			case L:
				toggleLeaderboard();
				break;
		}
	}

	private void toggleLeaderboard() {
		showingLeaderboard = !showingLeaderboard;

		if (!showingLeaderboard) return;

		try {
			Connection connection = DatabaseManager.getConnection(DATABASE_PATH);
			ScoreDaoImpl scoreDao = new ScoreDaoImpl(connection);
			List<Score> highscores = scoreDao.findAll().stream().limit(8).collect(Collectors.toList());
			leaderboardView.setHighscores(highscores);
			connection.close();
		} catch (Exception e) {
			showingLeaderboard = false;
			showErrorDialog(e);
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

	public void startGame() {
		gameController.startGame();
		startGameTime = Instant.now();
	}

	public void finishGame() {
		Platform.runLater(() -> {
			try {
				saveScore();
			}
			catch (Exception e) {
				showErrorDialog(e);
			}
		});
	}

	private void showErrorDialog(Exception e) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("An error occured");
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}

	private void saveScore() throws SQLException, ClassNotFoundException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYY-MM-dd HH:MM:ss");
		Date now = new Date();
		endGameTime = now.toInstant();
		Duration duration = Duration.between(startGameTime, endGameTime);

		System.out.println(dateFormat.format(now));

		Connection connection = DatabaseManager.getConnection(DATABASE_PATH);
		ScoreDaoImpl scoreDao = new ScoreDaoImpl(connection);

		String name = askName();
		Score score = new Score();
		score.setName(name);
		score.setScore(game.getScore());
		score.setFinishTime(dateFormat.format(now));
		score.setGameDuration(duration.getSeconds());

		scoreDao.save(score);
	}


	private String askName() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.initOwner(null);
		dialog.setTitle("What's your name?");
		Optional<String> result = dialog.showAndWait();
		return result.orElse("John Doe");
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

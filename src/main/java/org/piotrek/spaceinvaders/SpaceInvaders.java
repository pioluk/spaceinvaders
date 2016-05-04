package org.piotrek.spaceinvaders;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import org.piotrek.spaceinvaders.model.State;

public class SpaceInvaders extends Application {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private final String TITLE = "SpaceInvaders";

	private Stage primaryStage;

	private final AtomicInteger frameCount = new AtomicInteger(0);
	private final Timer fpsCounterTimer = new Timer();

	private final State state = new State();
	private final ScoreRenderer scoreRenderer = new ScoreRenderer();
	private final PlayerRenderer playerRenderer = new PlayerRenderer();

	private void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);

		scoreRenderer.setScore(state.getScore());
		playerRenderer.setX(state.getPlayerX());

		scoreRenderer.draw(graphicsContext);
		playerRenderer.draw(graphicsContext);
	}

	private AnimationTimer createAnimationTimer(GraphicsContext graphicsContext) {
		return new AnimationTimer() {
			@Override
			public void handle(long now) {
				draw(graphicsContext);
				frameCount.incrementAndGet();
			}
		};
	}

	private void startFPSCounterTimer() {
		fpsCounterTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					primaryStage.setTitle(TITLE + " (" + frameCount.get() + " FPS)");
					frameCount.set(0);
				});

			}
		}, 0, 1000);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle(TITLE);

		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

		state.setPlayerX(WIDTH / 2);

		AnimationTimer timer = createAnimationTimer(graphicsContext);
		timer.start();

		startFPSCounterTimer();

		scene.setOnKeyPressed(event -> handleKeyPressed(event));

		root.getChildren().add(canvas);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void handleKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
			case LEFT:
				state.playerMoveLeft();
				break;

			case RIGHT:
				state.playerMoveRight();
				break;
		}
	}

	@Override
	public void stop() {
		fpsCounterTimer.cancel();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

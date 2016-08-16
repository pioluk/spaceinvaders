package org.piotrek.spaceinvaders;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private Scene scene;
	private GraphicsContext graphicsContext;

	private Engine engine = new Engine();

	private AnimationTimer createAnimationTimer(GraphicsContext graphicsContext) {
		return new AnimationTimer() {
			@Override
			public void handle(long time) {
				engine.update(time);
				engine.render(graphicsContext);
			}
		};
	}

	private void attachEventHandlers() {
		scene.setOnKeyPressed(engine::handleKeyPressed);
	}

	private void loadCustomFonts() {
		Font.loadFont(getClass().getResourceAsStream("VT323-Regular.ttf"), 12.0);
	}

	private void initializeUI(Stage primaryStage) {
		primaryStage.setTitle(Config.WINDOW_TITLE);

		Group root = new Group();
		scene = new Scene(root);
		Canvas canvas = new Canvas(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void startAnimationTimer() {
		AnimationTimer timer = createAnimationTimer(graphicsContext);
		timer.start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		if (primaryStage != null) {
			primaryStage.close();
		}

		loadCustomFonts();
		initializeUI(primaryStage);
		attachEventHandlers();
		startAnimationTimer();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

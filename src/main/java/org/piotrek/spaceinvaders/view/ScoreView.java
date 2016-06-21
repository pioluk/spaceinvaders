package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.piotrek.spaceinvaders.Config;

public class ScoreView implements View {

	private static double FONT_SIZE = 20d;
	private static double X = 5d;
	private static double Y = 20d;
	private static double MAX_WIDTH = 200d;

	private int score = 0;
	private Font font = new Font(Config.DEFAULT_FONT_NAME, FONT_SIZE);

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		String scoreText = "SCORE: " + score;

		graphicsContext.setFill(Color.LIME);
		graphicsContext.setFont(font);
		graphicsContext.fillText(scoreText, X, Y, MAX_WIDTH);

		graphicsContext.restore();
	}
}

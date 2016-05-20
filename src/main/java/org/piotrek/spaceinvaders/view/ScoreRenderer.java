package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreRenderer implements View {

	private static final String FONT_NAME = "VT323";
	private static double FONT_SIZE = 16d;
	private static double X = 5d;
	private static double Y = 20d;
	private static double MAX_WIDTH = 200d;

	private int score = 0;
	private Font font = new Font(FONT_NAME, FONT_SIZE);

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		String scoreText = "SCORE: " + score;

		graphicsContext.setFill(Color.LIME);
		graphicsContext.setFont(font);
		graphicsContext.fillText(scoreText, X, Y, MAX_WIDTH);
	}
}

package org.piotrek.spaceinvaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreRenderer implements Renderer {

	private static double FONT_SIZE = 16d;
	private static double X = 5d;
	private static double Y = 20d;
	private static double MAX_WIDTH = 200d;

	private int score = 0;
	private Font font = new Font(FONT_SIZE);

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void draw(GraphicsContext graphicsContext) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SCORE: ");
		stringBuilder.append(score);

		String scoreText = stringBuilder.toString();

		graphicsContext.setFill(Color.LIME);
		graphicsContext.setFont(font);
		graphicsContext.fillText(scoreText, X, Y, MAX_WIDTH);
	}
}

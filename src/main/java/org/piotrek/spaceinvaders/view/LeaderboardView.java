package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Score;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardView implements View {

	private static final Font TITLE_FONT = new Font(Config.DEFAULT_FONT_NAME, 48.0);
	private static final Font FONT = new Font(Config.DEFAULT_FONT_NAME, 36.0);

	private List<Score> highscores = new ArrayList<>();

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		graphicsContext.setTextAlign(TextAlignment.CENTER);
		graphicsContext.setFill(Color.LIME);
		graphicsContext.setFont(TITLE_FONT);
		graphicsContext.fillText("LEADERBOARD", Config.WINDOW_WIDTH / 2.0, 96.0);

		graphicsContext.setFont(FONT);

		double scoreY = 96.0 + 48.0 + 48.0;

		for (Score score : highscores) {
			String scoreText = score.toString();
			graphicsContext.fillText(scoreText, Config.WINDOW_WIDTH / 2.0, scoreY);
			scoreY += 36.0 + 12.0;
		}

		graphicsContext.restore();
	}

	public void setHighscores(List<Score> highscores) {
		this.highscores = highscores;
	}
}

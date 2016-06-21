package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.piotrek.spaceinvaders.Config;

public class GameOverView implements View {

	private static final Font FONT = new Font(Config.DEFAULT_FONT_NAME, 96.0);

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		graphicsContext.setFont(FONT);
		graphicsContext.setFill(Color.LIME);
		graphicsContext.setTextAlign(TextAlignment.CENTER);
		graphicsContext.fillText("Game Over", Config.WINDOW_WIDTH / 2.0, Config.WINDOW_HEIGHT / 2.0);

		graphicsContext.restore();
	}
}

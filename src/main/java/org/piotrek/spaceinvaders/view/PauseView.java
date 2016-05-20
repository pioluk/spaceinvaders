package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.piotrek.spaceinvaders.Config;

public class PauseView implements View {

	private static final Font FONT = new Font(Config.DEFAULT_FONT_NAME, 48.0);
	private static final double HALF_WINDOW_WIDTH = Config.WINDOW_WIDTH / 2.0;
	private static final double HALF_WINDOW_HEIGHT = Config.WINDOW_HEIGHT / 2.0;
	private static final Color color = new Color(0.0, 0.0, 0.0, 0.6);

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		graphicsContext.setFill(color);
		graphicsContext.fillRect(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

		graphicsContext.setFont(FONT);
		graphicsContext.setTextAlign(TextAlignment.CENTER);

		graphicsContext.setFill(Color.LIME);
		graphicsContext.fillText("PAUSED", HALF_WINDOW_WIDTH, HALF_WINDOW_HEIGHT);

		graphicsContext.restore();
	}
}

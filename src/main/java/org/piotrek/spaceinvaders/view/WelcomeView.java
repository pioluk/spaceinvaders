package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.piotrek.spaceinvaders.Config;

public class WelcomeView implements View {

	private static final double VERTICAL_SPACE = 36.0;

	private static final Font titleFont = new Font(Config.DEFAULT_FONT_NAME, 72.0);
	private static final Font menuFont = new Font(Config.DEFAULT_FONT_NAME, 36.0);
	private static final Font footerFont = new Font(Config.DEFAULT_FONT_NAME, 28.0);
	private static final double HALF_WINDOW_WIDTH = Config.WINDOW_WIDTH / 2.0;

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		drawTitle(graphicsContext);
		drawMenu(graphicsContext);
		drawFooter(graphicsContext);

		graphicsContext.restore();
	}

	private void drawTitle(GraphicsContext graphicsContext) {
		graphicsContext.setFont(titleFont);
		graphicsContext.setTextAlign(TextAlignment.CENTER);
		graphicsContext.setFill(Color.LIME);
		graphicsContext.fillText("SPACE INVADERS", HALF_WINDOW_WIDTH, 64.0);
	}

	private void drawMenu(GraphicsContext graphicsContext) {
		double startHeight = 200.0;

		graphicsContext.setFont(menuFont);
		graphicsContext.fillText("SPACEBAR - start new game", HALF_WINDOW_WIDTH, startHeight);
		startHeight += VERTICAL_SPACE;
		graphicsContext.fillText("L - show leaderboard", HALF_WINDOW_WIDTH, startHeight);
		startHeight += VERTICAL_SPACE;
		graphicsContext.fillText("P - pause / unpause", HALF_WINDOW_WIDTH, startHeight);
		startHeight += VERTICAL_SPACE;
		graphicsContext.fillText("R - restart", HALF_WINDOW_WIDTH, startHeight);
		startHeight += VERTICAL_SPACE;
		graphicsContext.fillText("Q - quit", HALF_WINDOW_WIDTH, startHeight);
	}

	private void drawFooter(GraphicsContext graphicsContext) {
		double startHeight = Config.WINDOW_HEIGHT - VERTICAL_SPACE;
		graphicsContext.setFont(footerFont);
		graphicsContext.fillText("2016 - Piotr Łukomiak & Artur Mishustin", HALF_WINDOW_WIDTH, startHeight);
	}

}

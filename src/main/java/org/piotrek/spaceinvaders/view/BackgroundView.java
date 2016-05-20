package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import org.piotrek.spaceinvaders.Config;

public class BackgroundView implements View {

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.fillRect(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
	}
}

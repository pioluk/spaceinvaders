package org.piotrek.spaceinvaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerRenderer implements Renderer {

	private Image playerSprite;
	private double x;
	private final double y = 516d;

	public PlayerRenderer() {
		playerSprite = new Image(getClass().getResourceAsStream("player.png"));
	}

	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(playerSprite, x, y);
	}
}

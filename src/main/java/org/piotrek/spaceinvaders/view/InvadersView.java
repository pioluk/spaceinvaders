package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Invader;

import java.util.List;

public class InvadersView implements View {

	private static final Image invaderImage = new Image(
		InvadersView.class.getClassLoader().getResourceAsStream("invader.png"),
		Config.INVADER_WIDTH,
		Config.INVADER_HEIGHT,
		true,
		true
	);

	private List<Invader> invaders;

	public InvadersView(List<Invader> invaders) {
		this.invaders = invaders;
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		for (Invader invader : invaders) {
			graphicsContext.drawImage(invaderImage, invader.getX(), invader.getY());
		}

		graphicsContext.restore();
	}
}

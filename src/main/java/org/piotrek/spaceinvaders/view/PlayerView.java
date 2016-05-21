package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Player;

import javax.inject.Inject;

public class PlayerView implements View {

	private static final Image playerImage = new Image(
		PlayerView.class.getClassLoader().getResourceAsStream("player.png"),
		Config.PLAYER_WIDTH,
		Config.PLAYER_WIDTH,
		true,
		true
	);

	private Player player;

	@Inject
	public PlayerView(Player player) {
		this.player = player;
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(playerImage, player.getX(), player.getY());
	}
}

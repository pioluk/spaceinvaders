package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Projectile;

import java.util.List;

public class ProjectilesView implements View {

	private static final Color PROJECTILE_COLOR = Color.AZURE;

	private List<Projectile> projectiles;

	public ProjectilesView(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public void renderProjectile(GraphicsContext graphicsContext, Projectile projectile) {
		graphicsContext.fillRect(projectile.getX(), projectile.getY(),
			Config.PROJECTILE_WIDTH, Config.PROJECTILE_HEIGHT);
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.save();

		graphicsContext.setFill(PROJECTILE_COLOR);

		for (Projectile projectile : projectiles) {
			renderProjectile(graphicsContext, projectile);
		}

		graphicsContext.restore();
	}

}

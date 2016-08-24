package org.piotrek.spaceinvaders.controller;

import javafx.application.Platform;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Board;
import org.piotrek.spaceinvaders.model.Invader;
import org.piotrek.spaceinvaders.model.Player;
import org.piotrek.spaceinvaders.model.Projectile;

import java.util.function.IntConsumer;

public class BoardController {

	private Board board;
	private PlayerController playerController;

	public BoardController(Board board) {
		setBoard(board);
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}

	public void setBoard(Board board) {
		this.board = board;
		playerController = new PlayerController(board.getPlayer());
	}

	public void movePlayerLeft() {
		playerController.moveLeft();
	}

	public void movePlayerRight() {
		playerController.moveRight();
	}

	public void fireProjectile() {
		Player player = board.getPlayer();
		double playerX = player.getX();
		double playerY = player.getY();

		Projectile projectile = new Projectile();
		projectile.setX(playerX + Config.PLAYER_WIDTH / 2.0 - Config.PROJECTILE_WIDTH / 2.0);
		projectile.setY(playerY - Config.PROJECTILE_HEIGHT);

		board.addProjectile(projectile);
	}

	public void advanceProjectiles() {
		for (Projectile projectile : board.getProjectiles()) {
			projectile.advance();
			if (projectile.getY() < 0) {
				Platform.runLater(() -> board.removeProjectile(projectile));
			}
		}
	}

	public void detectCollisions() {
		if (board.getProjectiles().isEmpty() ||
			board.getInvaders().isEmpty()) {
			return;
		}

		for (Projectile projectile : board.getProjectiles()) {
			for (Invader invader : board.getInvaders()) {
				double projectileX = projectile.getX();
				double projectileY = projectile.getY();
				double invaderX = invader.getX();
				double invaderY = invader.getY();

				if (projectileX >= invaderX && projectileX - Config.PROJECTILE_WIDTH <= invaderX + Config.INVADER_WIDTH &&
					projectileY >= invaderY && projectileY <= invaderY + Config.INVADER_HEIGHT &&
					!invader.isDead()) {

					Platform.runLater(() -> {
						board.removeProjectile(projectile);
						invader.decreaseHP();
					});
				}
			}
		}
	}

	public void removeDeadInvaders() {
		board.getInvaders().removeIf(invader -> invader.isDead());
	}

	public void forEachDeadInvader(IntConsumer callback) {
		int points = board.getInvaders().stream()
			.filter(invader -> invader.isDead())
			.mapToInt(Invader::getPoints)
			.sum();
		callback.accept(points);
	}

	public void moveInvaders(long time, int level) {
		double delta = Math.sin(time / (16 * 10e7));
		for (Invader invader : board.getInvaders()) {
			invader.setX(invader.getX() + delta);
			invader.setY(invader.getY() + 0.1 * level);
		}
	}
}

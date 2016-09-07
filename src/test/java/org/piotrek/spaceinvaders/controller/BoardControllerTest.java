package org.piotrek.spaceinvaders.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoardControllerTest {

	private Board board;
	private BoardController boardController;

	@Before
	public void init() {
		Game game = new Game();
		board = BoardFactory.create(game.getLevel());
		board.setPlayer(new Player());
		boardController = new BoardController(board);
	}

	@Test
	public void shouldMovePlayerLeft() {
		double xPosBefore = board.getPlayer().getX();
		boardController.movePlayerLeft();
		double xPosAfter = board.getPlayer().getX();
		assertThat(xPosAfter, equalTo(xPosBefore - Config.SINGLE_MOVE_DISTANCE_FOR_PLAYER));
	}

	@Test
	public void shouldmovePlayerLeftAtLeftBorder() {
		board.getPlayer().setX(0);
		boardController.movePlayerLeft();
		Double xPosAfter = board.getPlayer().getX();
		assertThat(xPosAfter, equalTo(0.0));
	}

	@Test
	public void shouldMovePlayerLeftAtOuside() {
		board.getPlayer().setX(-20);
		boardController.movePlayerLeft();
		Double xPosAfter = board.getPlayer().getX();
		assertThat(xPosAfter, equalTo(0.0));
	}

	@Test
	public void shouldMovePlayerRight() {
		double xPosBefore = board.getPlayer().getX();
		boardController.movePlayerRight();
		Double xPosAfter = board.getPlayer().getX();
		assertThat(xPosAfter, equalTo(xPosBefore + Config.SINGLE_MOVE_DISTANCE_FOR_PLAYER));
	}

	@Test
	public void shouldMovePlayerRightAtRightBorder() {
		board.getPlayer().setX(Config.WINDOW_WIDTH);
		boardController.movePlayerRight();
		Double xPosAfter = board.getPlayer().getX();
		assertThat(xPosAfter, equalTo(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH));
	}

	@Test
	public void shouldMovePlayerRightAtOutside() {
		board.getPlayer().setX(Config.WINDOW_WIDTH + 200);
		boardController.movePlayerRight();
		Double xPosAfter = board.getPlayer().getX();
		assertThat(xPosAfter, equalTo(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH));
	}

	@Test
	public void shouldFireNewProjectile() {
		List<Projectile> projectiles = board.getProjectiles();

		int numberOfProjectilesBeforeFiring = projectiles.size();

		boardController.fireProjectile();

		int numberOfProjectilesAfterFiring = projectiles.size();

		assertThat(numberOfProjectilesBeforeFiring, equalTo(numberOfProjectilesAfterFiring - 1));
	}

	@Test
	public void shouldFireProjectileAtCorrectCoordinates() {
		Player player = board.getPlayer();
		double projectileX = player.getX() + Config.PLAYER_WIDTH / 2.0 - Config.PROJECTILE_WIDTH / 2.0;
		double projectileY = player.getY() - Config.PROJECTILE_HEIGHT;

		boardController.fireProjectile();

		List<Projectile> projectiles = board.getProjectiles();
		Projectile lastProjectile = projectiles.get(projectiles.size() - 1);

		assertThat(projectileX, equalTo(lastProjectile.getX()));
		assertThat(projectileY, equalTo(lastProjectile.getY()));
	}

	private void waitForThread() throws InterruptedException {
		Semaphore semaphore = new Semaphore(0);
		Platform.runLater(() -> semaphore.release());
		semaphore.acquire();
	}

	@Test
	public void shouldAdvanceProjectiles() {
		Projectile projectile = new Projectile();

		double initialPosition = 100d;

		projectile.setY(initialPosition);

		board.addProjectile(projectile);

		boardController.advanceProjectiles();

		assertThat(projectile.getY(), equalTo(initialPosition - Config.PROJECTILE_ADVANCE_STEP));
	}

	@Test
	public void shouldRemoveOffscreenProjectiles() throws InterruptedException {
		Projectile projectile1 = new Projectile();
		Projectile projectile2 = new Projectile();
		Projectile projectile3 = new Projectile();

		projectile1.setY(10d);
		projectile2.setY(1d);
		projectile3.setY(-1d);

		board.addProjectile(projectile1);
		board.addProjectile(projectile2);
		board.addProjectile(projectile3);

		new JFXPanel();
		boardController.advanceProjectiles();

		waitForThread();

		assertThat(board.getProjectiles().size(), equalTo(1));
	}

	@Test
	public void shouldDetectCollisions1() throws Exception {
		Projectile projectile = new Projectile();
		projectile.setX(2.01);
		projectile.setY(2.01);

		board.addProjectile(projectile);

		Invader invader = new Invader(1, 1);
		invader.setX(2.0);
		invader.setY(2.0);
		List<Invader> invanders = new ArrayList<>();
		invanders.add(invader);

		new JFXPanel();

		board.setInvaders(invanders);

		boardController.detectCollisions();
		waitForThread();

		assertTrue(board.getProjectiles().isEmpty());
	}

	@Test
	public void shouldDetectCollisions2() throws Exception {
		Projectile projectile = new Projectile();

		projectile.setX(1.0);
		projectile.setY(1.0);
		board.getProjectiles().clear();
		board.addProjectile(projectile);

		Invader invader = new Invader(1, 1);
		invader.setX(2.0);
		invader.setY(2.0);
		ArrayList<Invader> invanders = new ArrayList<>();
		invanders.add(invader);

		new JFXPanel();

		board.setInvaders(invanders);

		boardController.detectCollisions();
		waitForThread();

		assertThat(board.getProjectiles().size(), equalTo(1));
	}

	@Test
	public void shouldRemoveDeadInvaders() {
		ArrayList<Invader> invanders = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			invanders.add(new Invader(1, 0));
		}

		invanders.get(1).decreaseHP();
		invanders.get(6).decreaseHP();
		invanders.get(7).decreaseHP();
		invanders.get(7).decreaseHP();

		board.setInvaders(invanders);
		boardController.removeDeadInvaders();

		assertThat(board.getInvaders().size(), equalTo(7));
	}
}


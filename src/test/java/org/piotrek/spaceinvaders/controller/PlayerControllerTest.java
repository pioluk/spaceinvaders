package org.piotrek.spaceinvaders.controller;

import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Player;

import static org.assertj.core.api.Assertions.*;

public class PlayerControllerTest {

	private Player player;
	private PlayerController playerController;

	@Before
	public void initialize() {
		player = new Player();
		playerController = new PlayerController(player);
	}

	@Test
	public void shouldMovePlayerLeft() {
		double x = player.getX();

		playerController.moveLeft();
		assertThat(player.getX()).isLessThan(x);
	}

	@Test
	public void shouldMovePlayerRight() {
		double x = player.getX();

		playerController.moveRight();
		assertThat(player.getX()).isGreaterThan(x);
	}

	@Test
	public void shouldNotAllowPlayerToExceedLeftWindowBound() {
		for (int i = 0; i < 100; ++i) {
			playerController.moveLeft();
		}

		assertThat(player.getX()).isGreaterThanOrEqualTo(0.0);
	}

	@Test
	public void shouldNotAllowPlayerToExceedRightWindowBound() {
		for (int i = 0; i < 100; ++i) {
			playerController.moveRight();
		}

		assertThat(player.getX()).isLessThanOrEqualTo(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH);
	}

}

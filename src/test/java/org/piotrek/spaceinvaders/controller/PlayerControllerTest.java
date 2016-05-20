package org.piotrek.spaceinvaders.controller;

import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.Player;

import static org.junit.Assert.*;
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

		playerController.movePlayerLeft();
		assertThat(player.getX()).isLessThan(x);
	}

	@Test
	public void shouldMovePlayerRight() {
		double x = player.getX();

		playerController.movePlayerRight();
		assertThat(player.getX()).isGreaterThan(x);
	}

	@Test
	public void shouldNotAllowPlayerToExceedLeftWindowBound() {
		for (int i = 0; i < 100; ++i) {
			playerController.movePlayerLeft();
		}

		assertThat(player.getX()).isGreaterThanOrEqualTo(0.0);
	}

	@Test
	public void shouldNotAllowPlayerToExceedRightWindowBound() {
		for (int i = 0; i < 100; ++i) {
			playerController.movePlayerLeft();
		}

		assertThat(player.getX()).isLessThanOrEqualTo(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH);
	}

}
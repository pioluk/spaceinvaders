package org.piotrek.spaceinvaders;

import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.controller.GameController;
import org.piotrek.spaceinvaders.controller.PlayerController;
import org.piotrek.spaceinvaders.model.Player;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class EngineTest {

	private Engine sut;

	@Before
	public void initialize() {
		sut = new Engine();
	}

	@Test
	public void shouldNotAllowToMovePlayerWhenGameIsNotStarted() {
		PlayerController mockPlayerController = mock(PlayerController.class);
		sut.setPlayerController(mockPlayerController);

		sut.movePlayerLeft();
		verify(mockPlayerController, never()).movePlayerLeft();

		sut.movePlayerRight();
		verify(mockPlayerController, never()).movePlayerRight();
	}

	@Test
	public void shouldNotAllowToMovePlayerWhenGameIsPaused() {
		sut.startGame();
		sut.togglePaused();

		PlayerController mockPlayerController = mock(PlayerController.class);
		sut.setPlayerController(mockPlayerController);

		sut.movePlayerLeft();
		verify(mockPlayerController, never()).movePlayerLeft();

		sut.movePlayerRight();
		verify(mockPlayerController, never()).movePlayerRight();
	}

}
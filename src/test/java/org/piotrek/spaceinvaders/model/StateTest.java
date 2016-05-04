package org.piotrek.spaceinvaders.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class StateTest {

	@Test
	public void playerMoveLeftShouldChangeXPosition() {
		State sut = new State();
		sut.setPlayerX(20);

		sut.playerMoveLeft();

		assertThat(sut.getPlayerX()).isEqualTo(10);
	}

	@Test
	public void playerMoveRightShouldChangeXPosition() {
		State sut = new State();
		sut.setPlayerX(20);

		sut.playerMoveRight();

		assertThat(sut.getPlayerX()).isEqualTo(30);
	}

}
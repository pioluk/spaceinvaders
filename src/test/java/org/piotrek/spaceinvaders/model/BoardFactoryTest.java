package org.piotrek.spaceinvaders.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BoardFactoryTest {

	@Test
	public void shouldCreateLevel1Board() {
		Board level1 = BoardFactory.create(1);
		assertTrue(level1 instanceof Level1Board);
	}

	@Test
	public void shouldCreateLevel2Board() {
		Board level2 = BoardFactory.create(2);
		assertTrue(level2 instanceof Level2Board);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailToCreateBoardAboveLevelCap() throws Exception {
		Board level10 = BoardFactory.create(10);
	}

}

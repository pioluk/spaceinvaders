package org.piotrek.spaceinvaders.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvanderFactoryTest {

	@Test
	public void InvaderFactoryTestType1() {
		Invader type1 = InvaderFactory.create(InvaderType.EASY);
		assertThat(type1.getPoints(), equalTo(5));
		assertThat(type1.hp, equalTo(1));
	}

	@Test
	public void InvaderFactoryTestType2() {
		Invader type1 = InvaderFactory.create(InvaderType.MEDIUM);
		assertThat(type1.getPoints(), equalTo(10));
		assertThat(type1.hp, equalTo(3));
	}

}

package org.piotrek.spaceinvaders.model;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ModelModule {

	@Provides
	@Singleton
	Game provideGame() {
		return new Game();
	}

	@Provides
	@Singleton
	Player providerPlayer() {
		return new Player();
	}

}

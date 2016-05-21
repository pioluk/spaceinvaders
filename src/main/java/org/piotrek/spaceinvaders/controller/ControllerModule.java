package org.piotrek.spaceinvaders.controller;

import dagger.Module;
import dagger.Provides;
import org.piotrek.spaceinvaders.model.Game;
import org.piotrek.spaceinvaders.model.Player;

import javax.inject.Singleton;

@Module
public class ControllerModule {

	@Provides
	@Singleton
	GameController provideGameController(Game game) {
		return new GameController(game);
	}

	@Provides
	@Singleton
	PlayerController providePlayerController(Player player) {
		return new PlayerController(player);
	}

}

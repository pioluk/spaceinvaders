package org.piotrek.spaceinvaders.view;

import dagger.Module;
import dagger.Provides;
import org.piotrek.spaceinvaders.model.Player;

import javax.inject.Singleton;

@Module
public class ViewModule {

	@Provides
	@Singleton
	public BackgroundView provideBackgroundView() {
		return new BackgroundView();
	}

	@Provides
	@Singleton
	public PauseView providePauseView() {
		return new PauseView();
	}

	@Provides
	@Singleton
	public PlayerView providePlayerView(Player player) {
		return new PlayerView(player);
	}

	@Provides
	@Singleton
	public WelcomeView provideWelcomeView() {
		return new WelcomeView();
	}

}

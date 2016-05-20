package org.piotrek.spaceinvaders;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.controller.PlayerController;
import org.piotrek.spaceinvaders.view.*;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EngineTest {

	private Engine sut;

	@Before
	public void initialize() {
		sut = new Engine();
	}

	@Test
	public void shouldAlwaysRenderBackgroundView() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View backgroundViewMock = mock(BackgroundView.class);
		sut.setBackgroundView(backgroundViewMock);

		sut.render(graphicsContext);

		sut.startGame();
		sut.render(graphicsContext);

		sut.togglePaused();
		sut.render(graphicsContext);

		sut.togglePaused();
		sut.render(graphicsContext);

		verify(backgroundViewMock, times(4)).render(graphicsContext);
	}

	@Test
	public void shouldRenderWelcomeViewWhenGameNotStarted() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View welcomeViewMock = mock(WelcomeView.class);
		sut.setBackgroundView(welcomeViewMock);

		sut.render(graphicsContext);
		verify(welcomeViewMock, times(1)).render(graphicsContext);
	}

	@Test
	public void shouldNotRenderWelcomeViewWhenGameStarted() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View welcomeViewMock = mock(WelcomeView.class);
		sut.setWelcomeView(welcomeViewMock);
		sut.startGame();

		sut.render(graphicsContext);
		verify(welcomeViewMock, never()).render(graphicsContext);
	}

	@Test
	public void shouldRenderPlayerViewWhenStarted() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View playerViewMock = mock(PlayerView.class);
		sut.setPlayerView(playerViewMock);
		sut.startGame();

		sut.render(graphicsContext);
		verify(playerViewMock, times(1)).render(graphicsContext);
	}

	@Test
	public void shouldNotRenderPlayerViewWhenNotStarted() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View playerViewMock = mock(PlayerView.class);
		sut.setPlayerView(playerViewMock);

		sut.render(graphicsContext);
		verify(playerViewMock, never()).render(graphicsContext);
	}

	@Test
	public void shouldRenderPlayerViewWhenStartedAndPaused() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View playerViewMock = mock(PlayerView.class);
		sut.setPlayerView(playerViewMock);
		sut.startGame();
		sut.togglePaused();

		sut.render(graphicsContext);
		verify(playerViewMock, times(1)).render(graphicsContext);
	}

	@Test
	public void shouldRenderPauseViewWhenPaused() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View pauseViewMock = mock(PauseView.class);
		sut.setPauseView(pauseViewMock);
		sut.startGame();
		sut.togglePaused();

		sut.render(graphicsContext);
		verify(pauseViewMock, times(1)).render(graphicsContext);
	}

	@Test
	public void shouldNotAllowToMovePlayerWhenGameIsNotStarted() {
		PlayerController playerControllerMock = mock(PlayerController.class);
		sut.setPlayerController(playerControllerMock);

		sut.movePlayerLeft();
		verify(playerControllerMock, never()).movePlayerLeft();

		sut.movePlayerRight();
		verify(playerControllerMock, never()).movePlayerRight();
	}

	@Test
	public void shouldNotAllowToMovePlayerWhenGameIsPaused() {
		sut.startGame();
		sut.togglePaused();

		PlayerController playerControllerMock = mock(PlayerController.class);
		sut.setPlayerController(playerControllerMock);

		sut.movePlayerLeft();
		verify(playerControllerMock, never()).movePlayerLeft();

		sut.movePlayerRight();
		verify(playerControllerMock, never()).movePlayerRight();
	}

}
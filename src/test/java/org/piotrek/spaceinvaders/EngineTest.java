package org.piotrek.spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.Engine;
import org.piotrek.spaceinvaders.MainApplication;
import org.piotrek.spaceinvaders.controller.PlayerController;
import org.piotrek.spaceinvaders.model.Player;
import org.piotrek.spaceinvaders.view.*;

import static org.mockito.Mockito.*;

public class EngineTest {

	private static Engine sut;
	private Scene scene;
	private GraphicsContext graphicsContext;
	private static Thread thread;

	@BeforeClass
	public static void initialize() throws InterruptedException {
		sut = new Engine();

		thread = new Thread("JavaFX Init Thread") {
			public void run() {
				Application.launch(MainApplication.class, new String[0]);
			}
		};
		thread.setDaemon(true);
		thread.start();
		System.out.printf("FX App thread started\n");
		Thread.sleep(500);
	}

	@After
	public void closeThread() throws InterruptedException {
		// sut.quitGame();
		// thread.interrupt();
	}

	@Test
	public void shouldAlwaysRenderBackgroundView() {
		Canvas canvas = new Canvas(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
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
		sut.setBackgroundView(playerViewMock);
		// sut.setPlayerView(playerViewMock);
		sut.startGame();

		sut.render(graphicsContext);
		verify(playerViewMock, times(1)).render(graphicsContext);
	}

	@Test
	public void shouldRenderPlayerViewWhenStartedAndPaused() {
		Canvas canvas = new Canvas();
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		View playerViewMock = mock(PlayerView.class);
		sut.setBackgroundView(playerViewMock);
		// sut.setPlayerView(playerViewMock);
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
		View playerViewMock = mock(PlayerView.class);
		sut.setBackgroundView(playerViewMock);
		sut.setPlayerController(playerControllerMock);

		sut.movePlayerLeft();
		verify(playerControllerMock, never()).moveLeft();

		sut.movePlayerRight();
		verify(playerControllerMock, never()).moveRight();
	}

	@Test
	public void shouldNotAllowToMovePlayerWhenGameIsPaused() {
		sut.startGame();
		sut.togglePaused();

		PlayerController playerControllerMock = mock(PlayerController.class);
		sut.setPlayerController(playerControllerMock);

		sut.movePlayerLeft();
		verify(playerControllerMock, never()).moveLeft();

		sut.movePlayerRight();
		verify(playerControllerMock, never()).moveRight();
	}

}

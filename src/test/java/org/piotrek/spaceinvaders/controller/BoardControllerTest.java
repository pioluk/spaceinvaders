package org.piotrek.spaceinvaders.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import org.piotrek.spaceinvaders.Config;
import org.piotrek.spaceinvaders.model.*;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Arie on 13.08.2016.
 */
public class BoardControllerTest {

    private Board board;
    private BoardController boardController;

    @Before
    public void init() {
        Game game = new Game();
        board = BoardFactory.create(game.getLevel());
        board.setPlayer(new Player());
        boardController = new BoardController(board);
    }

    @Test
    public void movePlayerLeft() {
        double xPosBefore = board.getPlayer().getX();
        boardController.movePlayerLeft();
        double xPosAfter = board.getPlayer().getX();
        assertThat(xPosAfter,equalTo(xPosBefore - Config.SINGLE_MOVE_DISTANCE_FOR_PLAYER));
    }

    @Test
    public void movePlayerLeftAtLeftBorder() {
        board.getPlayer().setX(0);
        boardController.movePlayerLeft();
        Double xPosAfter = board.getPlayer().getX();
        assertThat(xPosAfter,equalTo(0.0));
    }

    @Test
    public void movePlayerLeftAtOuside() {
        board.getPlayer().setX(-20);
        boardController.movePlayerLeft();
        Double xPosAfter = board.getPlayer().getX();
        assertThat(xPosAfter,equalTo(0.0));
    }

    @Test
    public void movePlayerRight() {
        double xPosBefore = board.getPlayer().getX();
        boardController.movePlayerRight();
        Double xPosAfter = board.getPlayer().getX();
        assertThat(xPosAfter,equalTo(xPosBefore + Config.SINGLE_MOVE_DISTANCE_FOR_PLAYER));
    }

    @Test
    public void movePlayerRightAtRightBorder() {
        board.getPlayer().setX(Config.WINDOW_WIDTH);
        boardController.movePlayerRight();
        Double xPosAfter = board.getPlayer().getX();
        assertThat(xPosAfter,equalTo(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH));
    }

    @Test
    public void movePlayerRightAtOutside() {
        board.getPlayer().setX(Config.WINDOW_WIDTH+200);
        boardController.movePlayerRight();
        Double xPosAfter = board.getPlayer().getX();
        assertThat(xPosAfter,equalTo(Config.WINDOW_WIDTH - Config.PLAYER_WIDTH));
    }

    @Test
    public void fireProjectileTest() {

        double projectileX = board.getPlayer().getX() + Config.PLAYER_WIDTH / 2.0 - Config.PROJECTILE_WIDTH / 2.0;
        double projectileY = board.getPlayer().getY() - Config.PROJECTILE_HEIGHT;
        int projectiveCountBefore = board.getProjectiles().size();

        boardController.fireProjectile();

        Projectile projectile = board.getProjectiles().get(board.getProjectiles().size()-1);

        assertThat(projectileX,equalTo(projectile.getX()));
        assertThat(projectileY,equalTo(projectile.getY()));
        assertThat(board.getProjectiles().size(),equalTo(++projectiveCountBefore));

    }

    @Test
    public void advanceProjecties() {
        //TODO finish this
    }


    /*
    public void advanceProjectiles() {
		for (Projectile projectile : board.getProjectiles()) {
			projectile.advance();
			if (projectile.getY() < 0) {
				Platform.runLater(() -> board.removeProjectile(projectile));
			}
		}
	}

	*/

    private void waitForThread() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Platform.runLater(() -> semaphore.release());
        semaphore.acquire();
    }

    @Test
    public void detectCollisionsTest1() throws  Exception {

        Projectile projectile = new Projectile();
        projectile.setX(2.01);
        projectile.setY(2.01);

        board.addProjectile(projectile);

        Invader invader = new Invader(1,1);
        invader.setX(2.0);
        invader.setX(2.0);
        ArrayList<Invader> invanders = new ArrayList<>();
        invanders.add(invader);

        new JFXPanel();

        board.setInvaders(invanders);

        boardController.detectCollisions();
        waitForThread();

        assertTrue(board.getProjectiles().isEmpty());
    }


    public void detectCollisionsTest2() throws  Exception {

        Projectile projectile = new Projectile();

        projectile.setX(1.0);
        projectile.setY(1.0);
        board.getProjectiles().clear();
        board.addProjectile(projectile);

        Invader invader = new Invader(1,1);
        invader.setX(2.0);
        invader.setX(2.0);
        ArrayList<Invader> invanders = new ArrayList<>();
        invanders.add(invader);

        new JFXPanel();

        board.setInvaders(invanders);

        boardController.detectCollisions();
        waitForThread();


        assertThat(board.getProjectiles().size(),equalTo(1));
    }

    @Test
    public void removeDeadInvadersTest() {

        ArrayList<Invader> invanders = new ArrayList<>();
        for(int i =0; i<10; i++) {
            invanders.add(new Invader(1,0));
        }

        invanders.get(1).decreaseHP();
        invanders.get(6).decreaseHP();
        invanders.get(7).decreaseHP();
        invanders.get(7).decreaseHP();

        board.setInvaders(invanders);
        boardController.removeDeadInvaders();

        assertThat(board.getInvaders().size(),equalTo(7));
    }

    @Test
    public void forEachDeadInvader() {
        ArrayList<Invader> invanders = new ArrayList<>();

        for(int i =0; i<10; i++) {
            invanders.add(new Invader(1,1));
        }

        invanders.get(1).decreaseHP();
        invanders.get(6).decreaseHP();
        invanders.get(7).decreaseHP();
        invanders.get(7).decreaseHP();

        board.setInvaders(invanders);

        Game game = new Game();
        GameController gc = new GameController(game);
        boardController.forEachDeadInvader(p -> gc.addToScore(p));

        assertThat(game.getScore(),equalTo(3));
    }

    @Test
    public void forEachDeadInvaderWithoutDeadInvader() {
        ArrayList<Invader> invanders = new ArrayList<>();

        for(int i =0; i<10; i++) {
            invanders.add(new Invader(1,1));
        }

        board.setInvaders(invanders);

        Game game = new Game();
        GameController gc = new GameController(game);
        boardController.forEachDeadInvader(p -> gc.addToScore(p));

        assertThat(game.getScore(),equalTo(0));
    }
}


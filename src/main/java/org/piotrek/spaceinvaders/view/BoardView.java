package org.piotrek.spaceinvaders.view;

import javafx.scene.canvas.GraphicsContext;
import org.piotrek.spaceinvaders.model.Board;

public class BoardView implements View {

	private Board board;

	private PlayerView playerView;
	private InvadersView invadersView;
	private ProjectilesView projectilesView;

	public BoardView(Board board) {
		setBoard(board);
	}

	public void setBoard(Board board) {
		this.board = board;
		this.playerView = new PlayerView(board.getPlayer());
		this.invadersView = new InvadersView(board.getInvaders());
		this.projectilesView = new ProjectilesView(board.getProjectiles());
	}

	@Override
	public void render(GraphicsContext graphicsContext) {
		playerView.render(graphicsContext);
		invadersView.render(graphicsContext);
		projectilesView.render(graphicsContext);
	}
}

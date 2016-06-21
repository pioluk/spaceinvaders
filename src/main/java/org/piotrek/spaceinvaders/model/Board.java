package org.piotrek.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {

	protected Player player;
	protected List<Invader> invaders;
	protected List<Projectile> projectiles = new ArrayList<>();

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Invader> getInvaders() {
		return invaders;
	}

	public void setInvaders(List<Invader> invaders) {
		this.invaders = invaders;
	}

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public void removeProjectile(Projectile projectile) {
		projectiles.remove(projectile);
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void removeInvader(Invader invader) {
		invaders.remove(invader);
	}
}

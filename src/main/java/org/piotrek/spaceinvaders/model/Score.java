package org.piotrek.spaceinvaders.model;

public class Score implements Comparable<Score> {
	public String name;
	public int score;
	public String finishTime;
	public long gameDuration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public long getGameDuration() {
		return gameDuration;
	}

	public void setGameDuration(long gameTime) {
		this.gameDuration = gameTime;
	}

	@Override
	public int compareTo(Score other) {
		if (score > other.score) {
			return 1;
		}
		else if (score < other.score) {
			return -1;
		}
		else {
			return finishTime.compareTo(other.finishTime);
		}
	}

	@Override
	public String toString() {
		return "" + name +
			" " + score +
			" " + finishTime +
			" " + gameDuration;
	}
}

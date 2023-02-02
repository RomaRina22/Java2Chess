package model;

import java.util.ArrayList;

public class MovePattern {
	private int direction;
	private int minDistance;
	private int maxDistance;
	private Boolean passthrough;
	private ArrayList<MovePattern> nextMove;
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getMinDistance() {
		return minDistance;
	}
	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}
	public int getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	public Boolean getPassthrough() {
		return passthrough;
	}
	public void setPassthrough(Boolean passthrough) {
		this.passthrough = passthrough;
	}
	public ArrayList<MovePattern> getNextMove() {
		return nextMove;
	}
	public void setNextMove(ArrayList<MovePattern> nextMove) {
		this.nextMove = nextMove;
	}
	public MovePattern(int direction, int minDistance, int maxDistance, Boolean passthrough,
			ArrayList<MovePattern> nextMove) {
		super();
		this.direction = direction;
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
		this.passthrough = passthrough;
		this.nextMove = nextMove;
	}
	
}

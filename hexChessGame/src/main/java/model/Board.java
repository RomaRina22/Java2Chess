package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Board {
	private String name;
	private Spot[][] spots;
	private int[] playerDirections = {1,4,0,0,0,0}; //default player movement directions 
	private int currentPlayerTurn = 0;
	private int size;
	
	public Board (int size, String name) {
		this.name = name;
		this.size = size;
		this.spots = new Spot[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				spots[i][j] = new Spot(i, j);
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(int currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public int getSize() {
		return size;
	}

	public void setPlayerDirections(int[] playerDirections) {
		this.playerDirections = playerDirections;
	}

	private Spot getSpotByCoords (int[] coords) {
		if (coords[0] > size || coords[1] > size) {
			return null;
		}
		else {
			return spots[coords[0]][coords[1]];
		}
	}
	
	public void resetSpotStates () { //wipes spot states to calm if not locked
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!spots[i][j].getState().equals(SpotStates.LOCKED)) {
					spots[i][j].setState(SpotStates.CALM);
				}
			}
		}
	}
	
	public boolean selectPiece (Spot target) {
		if (target.getPiece().getOwner().equals(GameSettings.allPlayers.get(currentPlayerTurn))) {
			target.setState(SpotStates.SELECTED);
			return true;
		}
		return false;
	}
	
	public boolean movePiece (Spot origin, Spot target) {
		if (origin.getState().equals(SpotStates.SELECTED) && (target.getState().equals(SpotStates.AVAILABLE) || target.getState().equals(SpotStates.TARGETTED))) { 
			target.setPiece(origin.removePiece());
			return true;
		}
		return false;
	}
	
	public void updateSpotStates (Spot origin, HashSet<Spot> targets) {
		Player owner = origin.getPiece().getOwner();
		for (Spot temp: targets) {
			if (temp.getPiece() == null) {
				if (temp.getState() != SpotStates.LOCKED) {
					temp.setState(SpotStates.AVAILABLE);
				}
			}
			else if (temp.getPiece().getOwner() != owner) {
				temp.setState(SpotStates.TARGETTED);
			}
		}
	}
	
	public HashSet<Spot> getAvailableMoves (Spot origin) {
		Player owner = origin.getPiece().getOwner();
		int ownerDirection = playerDirections[GameSettings.allPlayers.indexOf(owner)];
		PieceType piece = origin.getPiece().getType();
		ArrayList<MovePattern> moves = piece.getMoves();
		HashSet<Spot> returnSpots = new HashSet<>();
		returnSpots = movesFrom (origin, moves, ownerDirection);
		return returnSpots;		
	}
	
	private HashSet<Spot> movesFrom (Spot origin, ArrayList<MovePattern> moves, int ownerDirection) {
		HashSet<Spot> returnSpots = new HashSet<>();
		if (moves == null) {
			returnSpots.add(origin);
		}
		else {
			Spot turtle;
			for (MovePattern currentMove: moves) {
				turtle = origin;
				int movingDirection = currentMove.getDirection() + ownerDirection-1;
				int i = 0;
				while (i <= currentMove.getMaxDistance()) {
					turtle = getSpotByCoords(turtle.getNeighborCoords(movingDirection)); //move turtle forward by one space
					if (turtle == null) {break;} //if going off board stop moving
					if (turtle.getPiece() != null && !currentMove.getPassthrough()) { //if current spot is occupied and piece cant pass add the spot to moves and go to next move
						returnSpots.add(turtle);
						break;
					}
					if (i >= currentMove.getMinDistance()) { //if within movement area do next move
						returnSpots.addAll(movesFrom(turtle, currentMove.getNextMove(), ownerDirection));
					}
					i++;
				}
			}
		}
		return returnSpots;
	}
	
	public void nextTurn() {
		currentPlayerTurn++;
		if (playerDirections[currentPlayerTurn] == 0 || currentPlayerTurn > 6) {
			currentPlayerTurn = 0;
		}
	}

	@Override
	public String toString() {
		String retstr = "";
		for (int i = 0; i < size;i++) {
			for (int j = 0; j < size; j++) {
				retstr += String.format("%7d", spots[j][i].getPiece());
			}
			retstr += "\n";
		}
		return retstr;
	}
	
	
}

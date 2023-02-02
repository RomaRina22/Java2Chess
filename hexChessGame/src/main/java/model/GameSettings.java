package model;

import java.util.ArrayList;

public class GameSettings {
	private Board ActiveBoard;
	public static ArrayList<Board> allBoards = new ArrayList<>();
	private GameState gameState;
	private ArrayList<Player> players;
	private int gameTurn;
	public Board getActiveBoard() {
		return ActiveBoard;
	}
	public void setActiveBoard(Board activeBoard) {
		ActiveBoard = activeBoard;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public int getGameTurn() {
		return gameTurn;
	}
	public void setGameTurn(int gameTurn) {
		this.gameTurn = gameTurn;
	}
	
	
}

package model;

import java.util.ArrayList;

public class GameSettings {
	private Board ActiveBoard;
	public static ArrayList<Board> allBoards = new ArrayList<>();
	private GameState gameState;
	private ArrayList<Player> players;
	private int gameTurn;
}

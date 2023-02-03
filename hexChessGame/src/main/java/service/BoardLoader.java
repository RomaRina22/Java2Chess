package service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import java2.hexChess.hexChessGame.App;
import javafx.scene.image.Image;
import model.*;

public class BoardLoader {
	static ObjectMapper mymapper = new ObjectMapper();
	private static HashMap<String,PieceType> pieces = new HashMap<>();
	
	public void initpieces() { //MovePattern(direction,mindist,maxdist,passthrough,nextmove)
		
		ArrayList<MovePattern> pawnMoves = new ArrayList<>();
		pawnMoves.add(new MovePattern(1, 1, 1, false, null));
		pawnMoves.add(new MovePattern(2, 1, 1, false, null));
		PieceType pawn = new PieceType("pawn",new Image(getClass().getResourceAsStream("/img/pawn.png")),pawnMoves);
		System.out.print("got pawn image!");
		
		ArrayList<MovePattern> queenMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			queenMoves.add(new MovePattern(i, 1, 100, false, null));
		}
		PieceType queen = new PieceType("queen",new Image(getClass().getResourceAsStream("/img/queen.png")),queenMoves);
		
		ArrayList<MovePattern> kingMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			kingMoves.add(new MovePattern(i, 1, 1, false, null));
		}
		PieceType king = new PieceType("king", new Image(getClass().getResourceAsStream("/img/king.png")), kingMoves);
		
		ArrayList<MovePattern> rookMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			if (i == 1 || i == 2 || i == 4 || i == 5) {
				rookMoves.add(new MovePattern(i, 1, 1, false, null));
			}
			else {
				rookMoves.add(new MovePattern(i, 1, 100, false, null));
			}
		}
		PieceType rook = new PieceType("rook", new Image(getClass().getResourceAsStream("/img/rook.png")), rookMoves);
		
		ArrayList<MovePattern> knightMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			knightMoves.add(new MovePattern(i, 2, 2, true, null));
		}
		ArrayList<MovePattern> extra1 = new ArrayList<>();
		extra1.add(new MovePattern(3, 1, 1, false, null));
		extra1.add(new MovePattern(5, 1, 1, false, null));
		knightMoves.add(new MovePattern(1, 2, 2, true, extra1));
		
		ArrayList<MovePattern> extra2 = new ArrayList<>();
		extra2.add(new MovePattern(6, 1, 1, false, null));
		extra2.add(new MovePattern(4, 1, 1, false, null));
		knightMoves.add(new MovePattern(2, 2, 2, true, extra2));
		
		ArrayList<MovePattern> extra3 = new ArrayList<>();
		extra3.add(new MovePattern(1, 1, 1, false, null));
		extra3.add(new MovePattern(5, 1, 1, false, null));
		knightMoves.add(new MovePattern(3, 2, 2, true, extra3));
		
		ArrayList<MovePattern> extra4 = new ArrayList<>();
		extra4.add(new MovePattern(2, 1, 1, false, null));
		extra4.add(new MovePattern(6, 1, 1, false, null));
		knightMoves.add(new MovePattern(4, 2, 2, true, extra4));
		
		ArrayList<MovePattern> extra5 = new ArrayList<>();
		extra5.add(new MovePattern(3, 1, 1, false, null));
		extra5.add(new MovePattern(1, 1, 1, false, null));
		knightMoves.add(new MovePattern(5, 2, 2, true, extra5));
		

		ArrayList<MovePattern> extra6 = new ArrayList<>();
		extra6.add(new MovePattern(2, 1, 1, false, null));
		extra6.add(new MovePattern(4, 1, 1, false, null));
		knightMoves.add(new MovePattern(6, 2, 2, true, extra6));
		PieceType knight = new PieceType("knight", new Image(getClass().getResourceAsStream("/img/knight.png")), knightMoves);
		
		pieces.put("queen", queen);
		pieces.put("king", king);
		pieces.put("pawn", pawn);
		pieces.put("rook", rook);
		pieces.put("knight", knight);
	}
	
	public static Board board1() {
		int size = 11;
		Player player1 = App.GameSettings.allPlayers.get(0);
		Player player2 = App.GameSettings.allPlayers.get(1);
		Board boardOne = new Board(size, "board1");
		ArrayList<int[]> lockspots = new ArrayList<>();
		lockspots.add(new int[] {0,0});lockspots.add(new int[] {1,0});lockspots.add(new int[] {8,0});lockspots.add(new int[] {9,0});lockspots.add(new int[] {10,0});
		lockspots.add(new int[] {0,1});lockspots.add(new int[] {1,1});lockspots.add(new int[] {9,1});lockspots.add(new int[] {10,1});
		lockspots.add(new int[] {0,2});lockspots.add(new int[] {9,2});lockspots.add(new int[] {10,2});
		lockspots.add(new int[] {0,3});lockspots.add(new int[] {10,3});
		lockspots.add(new int[] {10,4});
		lockspots.add(new int[] {10,6});
		lockspots.add(new int[] {0,7});lockspots.add(new int[] {10,7});
		lockspots.add(new int[] {0,8});lockspots.add(new int[] {9,8});lockspots.add(new int[] {10,8});
		lockspots.add(new int[] {0,9});lockspots.add(new int[] {1,9});lockspots.add(new int[] {9,9});lockspots.add(new int[] {10,9});
		lockspots.add(new int[] {0,10});lockspots.add(new int[] {1,10});lockspots.add(new int[] {8,10});lockspots.add(new int[] {9,10});lockspots.add(new int[] {10,10});
		for (int[] coord: lockspots) {
			boardOne.getSpotByCoords(coord).setState(SpotStates.LOCKED);
		}
		for (int i = 0;i < size;i++) {
			Spot spot = boardOne.getSpotByCoords(new int[]{i,1});
			if (spot.getState().equals(SpotStates.CALM)) {
				spot.setPiece(new Piece(player2, pieces.get("pawn")));
			}
		}
		for (int i = 0;i < size;i++) {
			Spot spot = boardOne.getSpotByCoords(new int[]{i,9});
			if (spot.getState().equals(SpotStates.CALM)) {
				spot.setPiece(new Piece(player1, pieces.get("pawn")));
			}
		}
		boardOne.getSpotByCoords(new int[] {2, 0}).setPiece(new Piece(player2, pieces.get("rook")));
		boardOne.getSpotByCoords(new int[] {7, 0}).setPiece(new Piece(player2, pieces.get("rook")));
		boardOne.getSpotByCoords(new int[] {2, 10}).setPiece(new Piece(player1, pieces.get("rook")));
		boardOne.getSpotByCoords(new int[] {7, 10}).setPiece(new Piece(player1, pieces.get("rook")));
		boardOne.getSpotByCoords(new int[] {3, 0}).setPiece(new Piece(player2, pieces.get("knight")));
		boardOne.getSpotByCoords(new int[] {6, 0}).setPiece(new Piece(player2, pieces.get("knight")));
		boardOne.getSpotByCoords(new int[] {3, 10}).setPiece(new Piece(player1, pieces.get("knight")));
		boardOne.getSpotByCoords(new int[] {6, 10}).setPiece(new Piece(player1, pieces.get("knight")));
		boardOne.getSpotByCoords(new int[] {5, 0}).setPiece(new Piece(player2, pieces.get("king")));
		boardOne.getSpotByCoords(new int[] {5, 10}).setPiece(new Piece(player1, pieces.get("king")));
		boardOne.getSpotByCoords(new int[] {4, 0}).setPiece(new Piece(player2, pieces.get("queen")));
		boardOne.getSpotByCoords(new int[] {4, 10}).setPiece(new Piece(player1, pieces.get("queen")));
		
		
		System.out.println(boardOne);
		
		return boardOne;
		
	}
	
	public static Board board2() {
		int size = 6;
		Player player1 = App.GameSettings.allPlayers.get(0);
		Player player2 = App.GameSettings.allPlayers.get(1);
		Player player3 = App.GameSettings.allPlayers.get(2);
		Player player4 = App.GameSettings.allPlayers.get(3);
		Player player5 = App.GameSettings.allPlayers.get(4);
		Player player6 = App.GameSettings.allPlayers.get(5);
		
		Board boardTwo = new Board(size, "board2");
		boardTwo.setPlayerDirections(new int[] {1,2,3,4,5,6});
		
		boardTwo.getSpotByCoords(new int[] {1 , 1}).setPiece(new Piece(player1,pieces.get("pawn")));
		boardTwo.getSpotByCoords(new int[] {2 , 2}).setPiece(new Piece(player2,pieces.get("pawn")));
		boardTwo.getSpotByCoords(new int[] {3 , 1}).setPiece(new Piece(player3,pieces.get("pawn")));
		boardTwo.getSpotByCoords(new int[] {4 , 1}).setPiece(new Piece(player4,pieces.get("pawn")));
		boardTwo.getSpotByCoords(new int[] {3 , 4}).setPiece(new Piece(player5,pieces.get("pawn")));
		boardTwo.getSpotByCoords(new int[] {3 , 3}).setPiece(new Piece(player6,pieces.get("pawn")));
		
		
		return boardTwo;
	}
	
}

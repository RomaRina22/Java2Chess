package service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

public class BoardLoader {
	static ObjectMapper mymapper = new ObjectMapper();
	private static HashMap<String,PieceType> pieces = new HashMap<>();
	
	public void initpieces() { //MovePattern(direction,mindist,maxdist,passthrough,nextmove)
		
		ArrayList<MovePattern> pawnMoves = new ArrayList<>();
		pawnMoves.add(new MovePattern(1, 1, 1, false, null));
		pawnMoves.add(new MovePattern(2, 1, 1, false, null));
		PieceType pawn = new PieceType("pawn",getClass().getResource("/img/pawn.png"),pawnMoves);
		
		ArrayList<MovePattern> queenMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			queenMoves.add(new MovePattern(i, 1, 100, false, null));
		}
		PieceType queen = new PieceType("queen",getClass().getResource("/img/queen.png"),queenMoves);
		
		ArrayList<MovePattern> kingMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			kingMoves.add(new MovePattern(i, 1, 1, false, null));
		}
		PieceType king = new PieceType("king", getClass().getResource("/img/king.png"), null);
		
		ArrayList<MovePattern> rookMoves = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			if (i == 1 || i == 2 || i == 4 || i == 5) {
				rookMoves.add(new MovePattern(i, 1, 1, false, null));
			}
			else {
				rookMoves.add(new MovePattern(i, 1, 100, false, null));
			}
		}
		PieceType rook = new PieceType("rook", getClass().getResource("/img/rook.png"), rookMoves);
		
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
		PieceType knight = new PieceType("knight", getClass().getResource("/img/knight.png"), knightMoves);
		
		pieces.put("queen", queen);
		pieces.put("king", king);
		pieces.put("pawn", pawn);
		pieces.put("rook", rook);
		pieces.put("knight", knight);
	}
	
	public static Board board1() {
		Board boardOne = new Board(11, "board1");
		System.out.println(boardOne);
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
		System.out.println(boardOne);
		
		return boardOne;
		
	}
	
	
}

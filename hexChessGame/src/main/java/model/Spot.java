package model;

public class Spot {
	private Piece piece;
	private int[] coords;
	private SpotStates state = SpotStates.CALM;
	
	public Spot(int x, int y) {
		coords = new int[] {x, y};
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece removePiece() {
		Piece temp = this.piece;
		this.piece = null;
		return temp;
	}
	public SpotStates getState() {
		return state;
	}

	public void setState(SpotStates state) {
		this.state = state;
	}
	
	public int[] getNeighborCoords (int direction) {
		int x = coords[0];
		int y = coords[1];
		//field is treated as even-r according to https://www.redblobgames.com/grids/hexagons/#coordinates
		//first is even/odd row, second is direction, third is x and y offset
		int[][][] additionMatrix = new int[][][] {
			{{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,0}},
			{{-1,-1},{0,-1},{1,0},{0,1},{-1,1},{-1,0}}};
		int parity = y%2;
		direction += -1; //accounts for direction being 1-6
		x += additionMatrix[parity][direction][0];
		y += additionMatrix[parity][direction][1];
		return new int[] {x,y};
	}
	
}

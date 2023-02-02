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
	
}

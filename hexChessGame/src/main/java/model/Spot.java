package model;

public class Spot {
	private Piece piece;
	private SpotStates state = SpotStates.CALM;
	
	public Spot() {
		
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

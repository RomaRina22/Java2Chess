package model;

import java.net.URL;
import java.util.ArrayList;

public class PieceType {
	private String name;
	private URL image;
	private ArrayList<MovePattern> moves;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public URL getImage() {
		return image;
	}
	public void setImage(URL image) {
		this.image = image;
	}
	public ArrayList<MovePattern> getMoves() {
		return moves;
	}
	public void setMoves(ArrayList<MovePattern> moves) {
		this.moves = moves;
	}
	public PieceType(String name, URL image, ArrayList<MovePattern> moves) {
		super();
		setName(name);
		setImage(image);
		setMoves(moves);
	}
	
	
}

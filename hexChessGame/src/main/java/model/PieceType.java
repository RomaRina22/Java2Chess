package model;

import java.net.URL;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class PieceType {
	private String name;
	private Image image;
	private ArrayList<MovePattern> moves;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public ArrayList<MovePattern> getMoves() {
		return moves;
	}
	public void setMoves(ArrayList<MovePattern> moves) {
		this.moves = moves;
	}
	public PieceType(String name, Image image, ArrayList<MovePattern> moves) {
		super();
		setName(name);
		setImage(image);
		setMoves(moves);
	}
	
	
}

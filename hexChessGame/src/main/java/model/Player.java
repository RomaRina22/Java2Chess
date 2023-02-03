package model;

import javafx.scene.paint.Color;
import java2.hexChess.hexChessGame.OptionsController;

public class Player {
	static private int playercounter = 0;
	private String name;
	private int id;
	private Color color;
	public static Color temp;
	
	
	
	public Player (String name, Color color) {
		setName(name);
		id = playercounter++;
		setColor(color);
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getId() {
		return id;
	}
	public void Players() {
		
		this.name = "player1";
		this.color = null;
	}
	public void Players(String name, Color color) {
		
		this.name = name;
		this.color = color;
	}
	public static Color valueOf(model.Color selectedcolor) {
		return temp;
		
	}
	
	

	
	
	
}

package model;

import javafx.scene.paint.Color;

public class Player {
	static private int playercounter = 0;
	private String name;
	private int id;
	private Color color;
	
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
	
	
}

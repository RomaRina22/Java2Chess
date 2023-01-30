package model;

import java.util.ArrayList;

public class Board {
	private String name;
	private Spot[][] spots;
	private int[] playerdirections = {1,3,0,0,0,0};
	
	public Board (int size, String name) {
		this.name = name;
		this.spots = new Spot[size][size];
	}
	
}

package model;

import java.util.ArrayList;

public class Board {
	private String name;
	private Spot[][] spots;
	private int[] playerdirections = {1,3,0,0,0,0};
	
	public Board (int size, String name) {
		this.name = name;
		this.spots = new Spot[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				spots[i][j] = new Spot(i, j);
			}
		}
	}
	
}

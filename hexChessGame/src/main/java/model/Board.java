package model;

import java.util.ArrayList;

public class Board {
	private Spot[][] spots;
	private int[] playerdirections = {1,3,0,0,0,0};
	
	public Board (int size) {
		this.spots = new Spot[size][size];
	}
	
}

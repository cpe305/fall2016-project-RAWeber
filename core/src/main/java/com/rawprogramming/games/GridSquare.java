package com.rawprogramming.games;

import com.badlogic.gdx.math.Rectangle;
import com.rawprogramming.games.Towers.Tower;

public class GridSquare {
	
	private int x;
	private int y;
	private int length;
	private Tower tower;

	public GridSquare(int x, int y, int length){
		this.x = x;
		this.y = y;
		this.length = length;
		tower = null;
	}
	
	public boolean hasTower(){
		return tower != null;
	}
	
	public Tower getTower(){
		return tower;
	}
	
	public void setTower(Tower tower){
		this.tower = tower;
	}
	
	public int getLength(){
		return length;
	}
	
	public int getX(){
		return x * length;
	}
	
	public int getY(){
		return y * length;
	}
}

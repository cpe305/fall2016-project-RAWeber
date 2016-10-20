package com.rawprogramming.games;

import com.rawprogramming.games.Towers.Tower;

public class GridSquare {
	
	private int x;
	private int y;
	int offsetX;
	int offsetY;
	private int length;
	private Tower tower;

	public GridSquare(int x, int y, int offsetX, int offsetY, int length){
		this.x = x;
		this.y = y;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.length = length;
		tower = null;
	}
	
	public boolean checkTouch(float x, float y){
		return (x > getCoordX() && x < getCoordX() + length) && (y > getCoordY() && y < getCoordY() + length);
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
	
	public int getCoordX(){
		return x * length + offsetX;
	}
	
	public int getCoordY(){
		return y * length + offsetY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}

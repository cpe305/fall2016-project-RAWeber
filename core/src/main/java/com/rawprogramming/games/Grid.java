package com.rawprogramming.games;

import com.badlogic.gdx.graphics.Texture;

public class Grid {
	
	private GameApp game;
	
    private int rows;
    private int cols;
    private int offsetX;
    private int offsetY;
    
    private Texture tile;
    private int tileLen;
    
    private GridSquare[][] grid;
    
    public Grid(int rows, int cols, int offsetX, int offsetY, GameApp game){
    	this.game = game;
    	
        tile = game.manager.get("Tile.png", Texture.class);
        tileLen = 45;
        
        this.rows = rows;
        this.cols = cols;
        
        this.offsetX = offsetX;
        this.offsetY = offsetY - rows * tileLen;
        
        grid = new GridSquare[rows][cols];
        
        for(int row = 0; row < rows; row++){
        	for(int col = 0; col < cols; col++){
        		grid[row][col] = new GridSquare(col, row, this.offsetX, this.offsetY, tileLen);
        	}
        }
    }
    
    public void render(){
        for(int row = 0; row < rows; row++){
        	for(int col = 0; col < cols; col++){
        		game.batch.draw(tile, grid[row][col].getCoordX(), grid[row][col].getCoordY(), tileLen, tileLen);
        	}
        }
    }
    
    public boolean checkTouch(float x, float y){
    	return (x > offsetX && x < offsetX + getWidth()) && (y > offsetY && y < offsetY + getHeight());
    }
    
	
	public GridSquare getTouchedSquare(float x, float y){
        for(int row = 0; row < rows; row++){
        	for(int col = 0; col < cols; col++){
        		if(grid[row][col].checkTouch(x, y)){
        			return grid[row][col];
        		}
        	}
        }
        return null;
	}
    
    private int getWidth(){
    	return cols * tileLen;
    }
    
    private int getHeight(){
    	return rows * tileLen;
    }
}

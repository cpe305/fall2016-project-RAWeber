package com.rawprogramming.games;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.Towers.Tower;

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
        		if(grid[row][col].hasTower()){
        			game.batch.draw(game.manager.get("BasicTower.png", Texture.class), grid[row][col].getCoordX(), grid[row][col].getCoordY(), tileLen, tileLen);
        		}
        	}
        }
    }
    
    public boolean checkTouch(float x, float y){
    	return (x > offsetX && x < offsetX + getWidth()) && (y > offsetY && y < offsetY + getHeight());
    }
    
	
	public GridSquare getTouchedSquare(float x, float y){
        int gridX = ((int)x - offsetX)/tileLen;
        int gridY = ((int)y - offsetY)/tileLen;
        return getSquare(gridY, gridX);
	}
	
	public GridSquare getSquare(int row, int col){
		return grid[row][col];
	}
	
	public void placeTower(GridSquare square, Tower tower){
		square.setTower(tower);
	}
    
    private int getWidth(){
    	return cols * tileLen;
    }
    
    private int getHeight(){
    	return rows * tileLen;
    }
}

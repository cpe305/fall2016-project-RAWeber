package com.rawprogramming.games.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.GridSquare;

public class GameScreen implements Screen{
	
    private final GameApp game;

    private OrthographicCamera camera;
    
    private GridSquare[][] grid;
    private int rows;
    private int cols;
    
    private int offsetX;
    private int offsetY;
    
    private Texture tile;

    public GameScreen(GameApp game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        
        tile = game.manager.get("Tile.png", Texture.class);
        
        rows = 15;
        cols = 30;
        grid = new GridSquare[rows][cols];
        for(int row = 0; row < rows; row++){
        	for(int col = 0; col < cols; col++){
        		grid[row][col] = new GridSquare(col, row, 32);
        	}
        }
        
        offsetX = 50;
        offsetY = 200;
    }

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        for(int row = 0; row < rows; row++){
        	for(int col = 0; col < cols; col++){
        		game.batch.draw(tile, grid[row][col].getX() + offsetX, grid[row][col].getY() + offsetY);
        	}
        }
        game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
}

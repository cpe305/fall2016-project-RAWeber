package com.rawprogramming.games.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.Grid;
import com.rawprogramming.games.GridSquare;

public class GameScreen implements Screen{
	
    private final GameApp game;

    private OrthographicCamera camera;

    private Grid grid;

    public GameScreen(GameApp game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        
        grid = new Grid(10, 20, 50, (int)camera.viewportHeight - 50, game);
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
        grid.render();
        game.batch.end();
        
        if(Gdx.input.justTouched()){
        	Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(grid.checkTouch(touchPos.x, touchPos.y)){
            	GridSquare square = grid.getTouchedSquare(touchPos.x, touchPos.y);
            	//Place or select tower
            }
        }
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

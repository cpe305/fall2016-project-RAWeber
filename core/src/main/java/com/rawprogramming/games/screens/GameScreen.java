package com.rawprogramming.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.Grid;
import com.rawprogramming.games.grid.GridSquare;
import com.rawprogramming.games.grid.TowerSquare;
import com.rawprogramming.games.towers.TowerStore;

public class GameScreen implements Screen {

  private final GameApp game;
  
  private OrthographicCamera camera;

  private Grid grid;
  private TowerStore store;

  /**
   * Constructor for GameScreen.
   * @param game Reference to GameApp
   */
  public GameScreen(GameApp game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(true, 1280, 720);

    grid = new Grid(10, 20, 50, 50);
    store = new TowerStore(200, 50, (int) camera.viewportHeight - 150);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    GameApp.batch.setProjectionMatrix(camera.combined);

    GameApp.batch.begin();
    grid.render();
    store.render();
    GameApp.batch.end();

    if (Gdx.input.justTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      
      if (grid.checkTouch(touchPos.x, touchPos.y)) {
        GridSquare square = grid.getTouchedSquare(touchPos.x, touchPos.y);
        System.out.println(square.getRow() + " " + square.getCol());
        if (square instanceof TowerSquare && store.hasTowerSelected()) {
          grid.placeTower((TowerSquare)square, store.buyTower());
        }
      }
      
      if (store.checkTouch(touchPos.x, touchPos.y)) {
        store.selectTower(touchPos.x, touchPos.y);
      } else {
        store.deselectTower();
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

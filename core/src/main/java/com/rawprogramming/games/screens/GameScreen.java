package com.rawprogramming.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.enemies.Spawner;
import com.rawprogramming.games.grid.GridSquare;
import com.rawprogramming.games.grid.MapGrid;
import com.rawprogramming.games.towers.TowerStore;

public class GameScreen implements Screen {

  private final GameApp game;

  private OrthographicCamera camera;

  private MapGrid grid;
  private TowerStore store;
  private Spawner spawner;

  /**
   * Constructor for GameScreen.
   * 
   * @param game Reference to GameApp
   */
  public GameScreen(GameApp game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(true, 960, 540);

    grid = new MapGrid("Level1.level", 0, 0);
    store = new TowerStore(200, 0, 0);
    spawner = new Spawner(10, 0, 1, grid.getSpawnSquare());
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
    spawner.render();
    store.render();
    GameApp.batch.end();

    if (Gdx.input.justTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);

      if (grid.checkTouch(touchPos.x, touchPos.y)) {
        GridSquare square = grid.getTouchedSquare(touchPos.x, touchPos.y);

        if (!store.isEnabled()) {
          grid.setSelectedSquare(square);
          if (grid.checkAvailable(square)) {
            store.getGrid().setOffsetX(square.getCoordX() - GridSquare.SquareSize * 3 / 2);
            store.getGrid().setOffsetY(square.getCoordY() - GridSquare.SquareSize * 3 / 2);
            store.toggleEnabled();
          }
        } else {
          store.toggleEnabled();
          if (store.getGrid().checkTouch(touchPos.x, touchPos.y)) {
            store.selectTower(touchPos.x, touchPos.y);
            grid.placeTower(store.buyTower());
          }
        }
      }
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && spawner.waveComplete()) {
      spawner.sendWave();
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

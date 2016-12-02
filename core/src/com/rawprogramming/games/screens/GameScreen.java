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

/**
 * Game screen for actual play.
 * 
 * @author Robert
 *
 */
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

    grid = MapGrid.getInstance();
    store = TowerStore.getInstance();
    spawner = Spawner.getInstance();
  }

  @Override
  public void show() {
    // Unsupported at this time
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    GameApp.getSpritebatch().setProjectionMatrix(camera.combined);

    GameApp.getSpritebatch().begin();
    grid.render();
    spawner.render();
    store.render();
    GameApp.getSpritebatch().end();

    if (Gdx.input.justTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);

      processGridTouch(touchPos);
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && spawner.waveComplete()) {
      spawner.sendWave();
    }

    if (spawner.isGameOver()) {
      game.setScreen(new GameOverScreen(game));
    }
  }

  private void processGridTouch(Vector3 touchPos) {
    if (grid.checkTouch(touchPos.x, touchPos.y)) {
      GridSquare square = grid.getTouchedSquare(touchPos.x, touchPos.y);

      if (store.isEnabled() && store.getGrid().checkTouch(touchPos.x, touchPos.y)) {
        store.selectTower(touchPos.x, touchPos.y);
        grid.placeTower(store.buyTower());
        store.setEnabled(false);
        grid.setSelectedSquare(null);
      } else {
        if (store.isEnabled() && square == grid.getSelectedSquare()
            || !grid.checkAvailable(square)) {
          store.setEnabled(false);
          grid.setSelectedSquare(null);
        } else {
          grid.setSelectedSquare(square);
          store.getGrid().setOffsetX(square.getCoordX() - GridSquare.SIZE * 3 / 2);
          store.getGrid().setOffsetY(square.getCoordY() - GridSquare.SIZE * 3 / 2);
          store.setEnabled(true);
        }
      }
    }
  }

  @Override
  public void resize(int width, int height) {
    // Unsupported at this time
  }

  @Override
  public void pause() {
    // Unsupported at this time
  }

  @Override
  public void resume() {
    // Unsupported at this time
  }

  @Override
  public void hide() {
    // Unsupported at this time
  }

  @Override
  public void dispose() {
    // Unsupported at this time
  }
}

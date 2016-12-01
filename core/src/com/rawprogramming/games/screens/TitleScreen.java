package com.rawprogramming.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

/**
 * Title screen of the game.
 * 
 * @author Robert
 *
 */
public class TitleScreen implements Screen {

  private final GameApp game;

  private OrthographicCamera camera;

  private Texture background;
  private Texture titleTexture;
  private Texture startTexture;
  private float titleWidth;
  private float startWidth;
  private float startHeight;

  /**
   * Constructor for TitleScreen.
   * 
   * @param game Reference to GameApp
   */
  public TitleScreen(GameApp game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 960, 540);
    background = GameApp.getAssetManager().get("TowerTile.png", Texture.class);
    titleTexture = GameApp.getAssetManager().get("TitleText.png", Texture.class);
    startTexture = GameApp.getAssetManager().get("StartText.png", Texture.class);

    titleWidth = titleTexture.getWidth();
    startWidth = startTexture.getWidth();
    startHeight = startTexture.getHeight();
  }

  @Override
  public void show() {
    // Unsupported at this time
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    GameApp.getSpritebatch().setProjectionMatrix(camera.combined);

    GameApp.getSpritebatch().begin();
    GameApp.getSpritebatch().draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
    GameApp.getSpritebatch().draw(titleTexture, camera.viewportWidth / 2 - titleWidth / 2,
        camera.viewportHeight / 2);
    GameApp.getSpritebatch().draw(startTexture, camera.viewportWidth / 2 - startWidth / 2,
        camera.viewportHeight / 2 - startHeight);
    GameApp.getSpritebatch().end();

    if (Gdx.input.isTouched()) {
      game.setScreen(new GameScreen(game));
      dispose();
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

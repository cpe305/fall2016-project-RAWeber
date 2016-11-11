package com.rawprogramming.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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

  private BitmapFont font;
  private String titleText;
  private String startText;
  private float titleWidth;
  private float titleHeight;
  private float startWidth;

  /**
   * Constructor for TitleScreen.
   * 
   * @param game Reference to GameApp
   */
  public TitleScreen(GameApp game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 960, 540);

    font = new BitmapFont();
    font.getData().setScale(1.5f);
    titleText = "Welcome to TowerDefense!!!";
    startText = "Click the screen to begin";

    GlyphLayout layout = new GlyphLayout();
    layout.setText(font, titleText);
    titleWidth = layout.width;
    titleHeight = layout.height;

    layout.setText(font, startText);
    startWidth = layout.width;

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
    GameApp.batch.setProjectionMatrix(camera.combined);

    GameApp.batch.begin();
    font.draw(GameApp.batch, titleText, camera.viewportWidth / 2 - titleWidth / 2,
        camera.viewportHeight / 2 + titleHeight / 2 + 5);
    font.draw(GameApp.batch, startText, camera.viewportWidth / 2 - startWidth / 2,
        camera.viewportHeight / 2 - titleHeight / 2 - 5);
    GameApp.batch.end();

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

package com.rawprogramming.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.rawprogramming.games.GameApp;

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
   * @param game Reference to GameApp
   */
  public TitleScreen(GameApp game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1280, 720);

    // font = game.manager.get("roughday.ttf", BitmapFont.class);
    font = new BitmapFont();
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

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();
    font.draw(game.batch, titleText, camera.viewportWidth / 2 - titleWidth / 2,
        camera.viewportHeight / 2 + titleHeight / 2 + 5);
    font.draw(game.batch, startText, camera.viewportWidth / 2 - startWidth / 2,
        camera.viewportHeight / 2 - titleHeight / 2 - 5);
    game.batch.end();

    if (Gdx.input.isTouched()) {
      game.setScreen(new GameScreen(game));
      dispose();
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

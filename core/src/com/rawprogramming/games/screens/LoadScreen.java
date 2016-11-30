package com.rawprogramming.games.screens;

import java.security.Security;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.rawprogramming.games.GameApp;

public class LoadScreen implements Screen {

  private final GameApp game;

  private OrthographicCamera camera;

  private BitmapFont font;
  private String titleText;
  private float titleWidth;
  private float titleHeight;

  /**
   * Constructor for TitleScreen.
   * 
   * @param game Reference to GameApp
   */
  public LoadScreen(GameApp game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 960, 540);

    font = new BitmapFont();
    font.getData().setScale(1.5f);
    titleText = "Loading...";

    GlyphLayout layout = new GlyphLayout();
    layout.setText(font, titleText);
    titleWidth = layout.width;
    titleHeight = layout.height;
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
    font.draw(GameApp.getSpritebatch(), titleText, camera.viewportWidth / 2 - titleWidth / 2,
        camera.viewportHeight / 2 + titleHeight / 2 + 5);
    GameApp.getSpritebatch().end();
    
    if (GameApp.getAssetManager().update()) {
      game.setScreen(new TitleScreen(game));
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
package com.rawprogramming.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rawprogramming.games.screens.TitleScreen;

public class GameApp extends Game {
  public static SpriteBatch batch;
  public static AssetManager manager = new AssetManager();

  @Override
  public void create() {
    batch = new SpriteBatch();
    manager.load("TowerTile.png", Texture.class);
    manager.load("PathTile.png", Texture.class);
    manager.load("BasicTower.png", Texture.class);
    manager.load("BasicEnemy.png", Texture.class);
    manager.load("StoreBackground.png", Texture.class);

    this.setScreen(new TitleScreen(this));
  }

  @Override
  public void render() {
    super.render();
    manager.update();
  }

  @Override
  public void dispose() {
    batch.dispose();
    manager.dispose();
  }
}

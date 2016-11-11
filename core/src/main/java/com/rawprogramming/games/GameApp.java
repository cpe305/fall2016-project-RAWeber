package com.rawprogramming.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rawprogramming.games.screens.TitleScreen;

/**
 * Main game, renders the screens and manages assets.
 * 
 * @author Robert
 */
public class GameApp extends Game {
  private static SpriteBatch batch;
  private static AssetManager manager;

  @Override
  public void create() {
    manager = new AssetManager();
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

  public static SpriteBatch getSpritebatch() {
    return batch;
  }
  
  public static AssetManager getAssetManager() {
    return manager;
  }
}

package com.rawprogramming.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rawprogramming.games.screens.LoadScreen;

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
    manager.load("TowerTile.png", Texture.class);
    manager.load("PathTile.png", Texture.class);
    manager.load("EmptyTile.png", Texture.class);
    manager.load("BasicTower.png", Texture.class);
    manager.load("StoreBackground.png", Texture.class);
    manager.load("Projectile.png", Texture.class);
    manager.load("Impact.png", Texture.class);
    manager.load("Explosion.png", Texture.class);
    manager.load("Dud.png", Texture.class);
    manager.load("StrongTower.png", Texture.class);
    manager.load("AreaTower.png", Texture.class);
    manager.load("StrongAreaTower.png", Texture.class);
    manager.load("SelectedTower.png", Texture.class);
    manager.load("BasicEnemy.png", Texture.class);
    manager.load("FastEnemy.png", Texture.class);
    manager.load("StrongEnemy.png", Texture.class);
    manager.load("TitleText.png", Texture.class);
    manager.load("StartText.png", Texture.class);
    manager.load("StartOverText.png", Texture.class);
    manager.load("GameOverText.png", Texture.class);
    manager.load("WaveCompleted.png", Texture.class);

    this.setScreen(new LoadScreen(this));
  }

  @Override
  public void dispose() {
    batch.dispose();
    manager.dispose();
  }

  public static SpriteBatch getSpritebatch() {
    if (batch == null) {
      batch = new SpriteBatch();
    }
    return batch;
  }

  public static AssetManager getAssetManager() {
    if (manager == null) {
      manager = new AssetManager();
    }
    return manager;
  }
}

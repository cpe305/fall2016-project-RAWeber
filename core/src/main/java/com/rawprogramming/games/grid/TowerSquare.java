package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.towers.Tower;

public class TowerSquare extends GridSquare {

  private Tower tower;

  /**
   * Constructor for TowerSquare.
   * @param col Column in grid
   * @param row Row in grid
   * @param offsetX Initial x offset
   * @param offsetY Initial y offset
   * @param length Length of square side
   */
  public TowerSquare(int col, int row, int offsetX, int offsetY, int length) {
    super(col, row, offsetX, offsetY, length);
    tile = GameApp.manager.get("TowerTile.png", Texture.class);
    tower = null;
  }

  public boolean hasTower() {
    return tower != null;
  }

  public Tower getTower() {
    return tower;
  }

  public void setTower(Tower tower) {
    this.tower = tower;
  }
}

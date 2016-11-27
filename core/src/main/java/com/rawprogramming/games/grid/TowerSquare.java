package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.towers.Tower;

/**
 * GridSquare that can hold a tower.
 * 
 * @author Robert
 *
 */
public class TowerSquare extends GridSquare {

  private Tower tower;

  /**
   * Constructor for TowerSquare.
   * 
   * @param col Column in grid
   * @param row Row in grid
   * @param grid Reference to the grid this is in
   */
  public TowerSquare(int col, int row, Grid grid) {
    super(col, row, grid);
    tile = GameApp.getAssetManager().get("TowerTile.png", Texture.class);
    tower = null;
  }

  /**
   * Checks whether the square already holds a tower.
   * 
   * @return Returns if the square already has a tower
   */
  public boolean hasTower() {
    return tower != null;
  }

  public Tower getTower() {
    return tower;
  }

  public void setTower(Tower tower) {
    this.tower = tower;
    tower.setPosition(getPosition());
  }
}

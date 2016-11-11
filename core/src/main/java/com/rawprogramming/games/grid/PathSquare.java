package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

/**
 * GridSquare that enemies can traverse.
 * 
 * @author Robert
 *
 */
public class PathSquare extends GridSquare {

  private PathSquare nextSquare;

  /**
   * Constructor for TowerSquare.
   * 
   * @param col Column in grid
   * @param row Row in grid
   * @param grid Reference to the grid this is in
   */
  public PathSquare(int col, int row, Grid grid) {
    super(col, row, grid);
    tile = GameApp.manager.get("PathTile.png", Texture.class);
  }

  public PathSquare getNextSquare() {
    return nextSquare;
  }

  public void setNextSquare(PathSquare nextSquare) {
    this.nextSquare = nextSquare;
  }
}

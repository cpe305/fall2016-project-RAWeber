package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

public class PathSquare extends GridSquare {

  private PathSquare nextSquare;

  /**
   * Constructor for TowerSquare.
   * @param col Column in grid
   * @param row Row in grid
   * @param offsetX Initial x offset
   * @param offsetY Initial y offset
   * @param length Length of square side
   */
  public PathSquare(int col, int row, int offsetX, int offsetY, int length) {
    super(col, row, offsetX, offsetY, length);
    tile = GameApp.manager.get("PathTile.png", Texture.class);
  }

  public PathSquare getNextSquare() {
    return nextSquare;
  }

  public void setNextSquare(PathSquare nextSquare) {
    this.nextSquare = nextSquare;
  }
  public boolean isEqualToPosition(int x, int y)
  {
	  return getCenterX() == x && getCenterY() == y;
  }
}

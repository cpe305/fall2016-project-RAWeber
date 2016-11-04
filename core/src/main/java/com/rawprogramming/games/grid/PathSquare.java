package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

public class PathSquare extends GridSquare {

  private PathSquare nextSquare;

  /**
   * Constructor for TowerSquare.
   * 
   * @param col Column in grid
   * @param row Row in grid
   * @param offsetX Initial x offset
   * @param offsetY Initial y offset
   */
  public PathSquare(int col, int row, int offsetX, int offsetY) {
    super(col, row, offsetX, offsetY);
    tile = GameApp.manager.get("PathTile.png", Texture.class);
  }

  public PathSquare getNextSquare() {
    return nextSquare;
  }

  public void setNextSquare(PathSquare nextSquare) {
    this.nextSquare = nextSquare;
  }
}

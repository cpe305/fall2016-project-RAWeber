package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GridSquare {
  
  public static final int SquareSize = 60;

  private int col;
  private int row;
  private Grid grid;

  protected Texture tile;

  /**
   * Constructor for GridSquare.
   * 
   * @param col Column in grid
   * @param row Row in grid
   * @param grid Reference to the grid this is in
   */
  public GridSquare(int col, int row, Grid grid) {
    this.col = col;
    this.row = row;
    this.grid = grid;
  }

  public Vector2 getCenter() {
    return new Vector2(getCoordX() + SquareSize / 2, getCoordY() + SquareSize / 2);
  }

  public int getCoordX() {
    return col * SquareSize + grid.offsetX;
  }

  public int getCoordY() {
    return row * SquareSize + grid.offsetY;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public Texture getTile() {
    return tile;
  }
}

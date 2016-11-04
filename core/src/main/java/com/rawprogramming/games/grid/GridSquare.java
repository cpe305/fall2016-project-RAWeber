package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GridSquare {
  
  public static final int SquareSize = 32;

  private int col;
  private int row;
  private int offsetX;
  private int offsetY;

  protected Texture tile;

  /**
   * Constructor for GridSquare.
   * 
   * @param col Column in grid
   * @param row Row in grid
   * @param offsetX Initial x offset
   * @param offsetY Initial y offset
   */
  public GridSquare(int col, int row, int offsetX, int offsetY) {
    this.col = col;
    this.row = row;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }

  public Vector2 getCenter() {
    return new Vector2(getCoordX() + SquareSize / 2, getCoordY() + SquareSize / 2);
  }

  public int getCoordX() {
    return col * SquareSize + offsetX;
  }

  public int getCoordY() {
    return row * SquareSize + offsetY;
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

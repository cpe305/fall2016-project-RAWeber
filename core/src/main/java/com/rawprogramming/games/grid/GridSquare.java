package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;

public class GridSquare {

  private int col;
  private int row;
  int offsetX;
  int offsetY;
  private int length;

  protected Texture tile;

  /**
   * Constructor for GridSquare.
   * @param col Column in grid
   * @param row Row in grid
   * @param offsetX Initial x offset
   * @param offsetY Initial y offset
   * @param length Length of square side
   */
  public GridSquare(int col, int row, int offsetX, int offsetY, int length) {
    this.col = col;
    this.row = row;
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    this.length = length;
  }
  
  public int getCenteredX() {
    return getCoordX() + length / 2;
  }
  
  public int getCenteredY() {
    return getCoordY() + length / 2;
  }

  public int getLength() {
    return length;
  }

  public int getCoordX() {
    return col * length + offsetX;
  }

  public int getCoordY() {
    return row * length + offsetY;
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

package com.rawprogramming.games;

import com.rawprogramming.games.towers.Tower;

public class GridSquare {

  private int col;
  private int row;
  int offsetX;
  int offsetY;
  private int length;
  private Tower tower;

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
    tower = null;
  }

  public boolean checkTouch(float coordX, float coordY) {
    return (coordX > getCoordX() && coordX < getCoordX() + length)
        && (coordY > getCoordY() && coordY < getCoordY() + length);
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
}

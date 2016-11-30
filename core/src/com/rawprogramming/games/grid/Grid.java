package com.rawprogramming.games.grid;

import com.rawprogramming.games.GameApp;

/**
 * Class representing grid of squares.
 * 
 * @author Robert
 *
 */
public abstract class Grid {

  protected int offsetX;
  protected int offsetY;
  protected int rows;
  protected int cols;
  protected GridSquare[][] gridMap;

  protected Grid(int offsetX, int offsetY) {
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }

  public abstract void render();

  /**
   * Checks whether grid was touched.
   * 
   * @param coordX X coordinate of the touch
   * @param coordY Y coordinate of the touch
   * @return Returns whether the grid was touched or not
   */
  public boolean checkTouch(float coordX, float coordY) {
    return (coordX >= offsetX && coordX <= offsetX + getWidth())
        && (coordY >= offsetY && coordY <= offsetY + getHeight());
  }


  /**
   * Gets the square touched at the given coordinates.
   * 
   * @param coordX X coordinate of touch
   * @param coordY Y coordinate of touch
   * @return Returns square at given coordinates
   */
  public GridSquare getTouchedSquare(float coordX, float coordY) {
    int gridX = ((int) coordX - offsetX) / GridSquare.SIZE;
    int gridY = ((int) coordY - offsetY) / GridSquare.SIZE;
    return getSquare(gridY, gridX);
  }

  /**
   * Gets a square from the grid.
   * 
   * @param row Row of the square to get
   * @param col Column of the square to get
   * @return Returns the square requested
   */
  public GridSquare getSquare(int row, int col) {
    return gridMap[row][col];
  }

  private int getWidth() {
    return cols * GridSquare.SIZE;
  }

  private int getHeight() {
    return rows * GridSquare.SIZE;
  }
}

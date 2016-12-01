package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Class representing a square in the game.
 * 
 * @author Robert
 *
 */
public class GridSquare {

  public static final int SIZE = 60;

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
    return new Vector2(getCoordX() + SIZE / 2, getCoordY() + SIZE / 2);
  }
  
  public Vector2 getPosition() {
    return new Vector2(getCoordX(), getCoordY());
  }

  public int getCoordX() {
    return col * SIZE + grid.offsetX;
  }

  public int getCoordY() {
    return row * SIZE + grid.offsetY;
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

package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.towers.Tower;

public class Grid {

  private int rows;
  private int cols;
  private int offsetX;
  private int offsetY;

  private int tileLen;

  private GridSquare[][] grid;

  /**
   * Constructor for grid.
   * 
   * @param rows Number of rows in grid
   * @param cols Number of Columns in grid
   * @param offsetX Offset of grid along x axis
   * @param offsetY Offset of grid along y axis
   */
  public Grid(int rows, int cols, int offsetX, int offsetY) {

    tileLen = 45;

    this.rows = rows;
    this.cols = cols;

    this.offsetX = offsetX;
    this.offsetY = offsetY;

    grid = new GridSquare[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        grid[row][col] = new TowerSquare(col, row, this.offsetX, this.offsetY, tileLen);
      }
    }
  }

  /**
   * Renders the grid on screen.
   */
  public void render() {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        GridSquare square = grid[row][col];
        GameApp.batch.draw(square.getTile(), square.getCoordX(), square.getCoordY(), tileLen,
            tileLen);
        if (square instanceof TowerSquare && ((TowerSquare) square).hasTower()) {
          GameApp.batch.draw(GameApp.manager.get("BasicTower.png", Texture.class),
              grid[row][col].getCoordX(), grid[row][col].getCoordY(), tileLen, tileLen);
        }
      }
    }
  }

  public boolean checkTouch(float coordX, float coordY) {
    return (coordX > offsetX && coordX < offsetX + getWidth())
        && (coordY > offsetY && coordY < offsetY + getHeight());
  }


  /**
   * Gets the square touched at the given coordinates.
   * 
   * @param coordX X coordinate of touch
   * @param coordY Y coordinate of touch
   * @return Returns square at given coordinates
   */
  public GridSquare getTouchedSquare(float coordX, float coordY) {
    int gridX = ((int) coordX - offsetX) / tileLen;
    int gridY = ((int) coordY - offsetY) / tileLen;
    return getSquare(gridY, gridX);
  }

  protected GridSquare getSquare(int row, int col) {
    return grid[row][col];
  }

  public void placeTower(TowerSquare square, Tower tower) {
    square.setTower(tower);
  }

  private int getWidth() {
    return cols * tileLen;
  }

  private int getHeight() {
    return rows * tileLen;
  }
}

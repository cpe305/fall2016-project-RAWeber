package com.rawprogramming.games.grid;

import com.rawprogramming.games.GameApp;

public abstract class Grid {

  protected int offsetX;
  protected int offsetY;
  protected int rows;
  protected int cols;
  protected GridSquare[][] grid;

  /**
   * Constructor for grid.
   * 
   * @param offsetX Offset of grid along x axis
   * @param offsetY Offset of grid along y axis
   */
  public Grid(int offsetX, int offsetY) {
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }

  /**
   * Renders the grid on screen.
   */
  public void render() {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        GridSquare square = grid[row][col];
        GameApp.batch.draw(square.getTile(), square.getCoordX(), square.getCoordY(),
            GridSquare.SquareSize, GridSquare.SquareSize);
        if (square instanceof TowerSquare && ((TowerSquare) square).hasTower()) {
          GameApp.batch.draw(((TowerSquare) square).getTower().getSprite(), square.getCoordX(),
              square.getCoordY(), GridSquare.SquareSize, GridSquare.SquareSize);
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
    int gridX = ((int) coordX - offsetX) / GridSquare.SquareSize;
    int gridY = ((int) coordY - offsetY) / GridSquare.SquareSize;
    return getSquare(gridY, gridX);
  }

  public GridSquare getSquare(int row, int col) {
    return grid[row][col];
  }

  private int getWidth() {
    return cols * GridSquare.SquareSize;
  }

  private int getHeight() {
    return rows * GridSquare.SquareSize;
  }
}

package com.rawprogramming.games.grid;

public class StoreGrid extends Grid {
  
  /**
   * Constructor for StoreGrid.
   * @param rows Rows in grid
   * @param cols Columns in grid
   * @param offsetX Offset of grid along x axis
   * @param offsetY Offset of grid along y axis
   */
  public StoreGrid(int rows, int cols, int offsetX, int offsetY) {
    super(offsetX, offsetY);
    this.rows = rows;
    this.cols = cols;

    grid = new GridSquare[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        grid[row][col] = new TowerSquare(col, row, this.offsetX, this.offsetY, this.tileLen);
      }
    }
  }
  
  public TowerSquare getTouchedSquare(float coordX, float coordY) {
    return (TowerSquare)super.getTouchedSquare(coordX, coordY);
  }

  public TowerSquare getSquare(int row, int col) {
    return (TowerSquare)super.getSquare(row, col);
  }
}

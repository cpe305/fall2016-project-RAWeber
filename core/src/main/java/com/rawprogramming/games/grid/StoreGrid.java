package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

/**
 * Grid the represents a store for the user to purchase towers.
 * 
 * @author Robert
 *
 */
public class StoreGrid extends Grid {

  /**
   * Constructor for StoreGrid.
   * 
   * @param rows Rows in grid
   * @param cols Columns in grid
   * @param offsetX Offset of grid along x axis
   * @param offsetY Offset of grid along y axis
   */
  public StoreGrid(int rows, int cols, int offsetX, int offsetY) {
    super(offsetX, offsetY);
    this.rows = rows;
    this.cols = cols;

    mapGrid = new GridSquare[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        mapGrid[row][col] = new TowerSquare(col, row, this);
      }
    }
  }

  @Override
  public TowerSquare getTouchedSquare(float coordX, float coordY) {
    return (TowerSquare) super.getTouchedSquare(coordX, coordY);
  }

  @Override
  public TowerSquare getSquare(int row, int col) {
    return (TowerSquare) super.getSquare(row, col);
  }

  public void setOffsetX(int offsetX) {
    this.offsetX = offsetX;
  }

  public void setOffsetY(int offsetY) {
    this.offsetY = offsetY;
  }

  @Override
  public void render() {
    GameApp.batch.draw(GameApp.manager.get("StoreBackground.png", Texture.class), offsetX - 8.0f,
        offsetY - 4.0f, GridSquare.SIZE * 4.0f + 16.0f, GridSquare.SIZE + 24.0f);
    super.render();
  }
}

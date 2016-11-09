package com.rawprogramming.games.grid;

import com.badlogic.gdx.Gdx;
import com.rawprogramming.games.towers.Tower;

import java.util.Scanner;

public class MapGrid extends Grid {

  private PathSquare spawnSquare;
  private GridSquare selectedSquare;

  private static enum TileType {
    TOWERTILE, PATHTILE, SPAWNTILE
  }

  /**
   * Constructor for MapGrid.
   * 
   * @param file File to read in grid
   * @param offsetX Initial offset along x axis
   * @param offsetY Initial offset along y axis
   */
  public MapGrid(String file, int offsetX, int offsetY) {
    super(offsetX, offsetY);

    try {
      Scanner scanner = new Scanner(Gdx.files.internal(file).reader());

      this.rows = scanner.nextInt();
      this.cols = scanner.nextInt();

      grid = new GridSquare[rows][cols];

      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          TileType type = TileType.values()[scanner.nextInt()];
          switch (type) {
            case TOWERTILE:
              grid[row][col] = new TowerSquare(col, row, this);
              break;
            case PATHTILE:
              grid[row][col] = new PathSquare(col, row, this);
              break;
            case SPAWNTILE:
              spawnSquare = new PathSquare(col, row, this);
              grid[row][col] = spawnSquare;
              break;
            default:
              // Error or ignore
              break;
          }
        }
      }

      generatePath(spawnSquare);

      scanner.close();
    } catch (Exception exception) {
      exception.printStackTrace(System.err);
    }
  }

  private void generatePath(PathSquare square) {
    for (int col = -1; col <= 1; col++) {
      for (int row = -1; row <= 1; row++) {
        if ((col + row) == -1 || (col + row) == 1) {

          int newRow = square.getRow() + row;
          int newCol = square.getCol() + col;

          if (newRow >= 0 && newCol >= 0 && newRow < rows && newCol < cols) {
            if (checkPath(getSquare(newRow, newCol))) {
              PathSquare pathSquare = (PathSquare) getSquare(newRow, newCol);
              square.setNextSquare(pathSquare);
              generatePath(pathSquare);
            }
          }
        }
      }
    }
  }

  private boolean checkPath(GridSquare square) {
    return square instanceof PathSquare && ((PathSquare) square).getNextSquare() == null;
  }

  /**
   * Checks if square can hold tower, and places tower.
   * @param tower Tower to place in square
   */
  public void placeTower(Tower tower) {
    if (checkAvailable(selectedSquare)) {
      ((TowerSquare) selectedSquare).setTower(tower);
    }
  }

  public PathSquare getSpawnSquare() {
    return spawnSquare;
  }

  public void setSelectedSquare(GridSquare selectedSquare) {
    this.selectedSquare = selectedSquare;
  }

  public boolean checkAvailable(GridSquare square) {
    return square instanceof TowerSquare && !((TowerSquare) square).hasTower();
  }
}

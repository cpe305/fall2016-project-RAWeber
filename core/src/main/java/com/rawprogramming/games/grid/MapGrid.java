package com.rawprogramming.games.grid;

import com.badlogic.gdx.Gdx;
import com.rawprogramming.games.screens.GameScreen;
import com.rawprogramming.games.towers.Tower;

import java.util.Scanner;

/**
 * Class representing the grid to place towers on.
 * 
 * @author Robert
 *
 */
public class MapGrid extends Grid {

  private PathSquare spawnSquare;
  private GridSquare selectedSquare;

  private enum SquareType {
    TOWERSQUARE, PATHSQUARE, SPAWNSQUARE
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

      mapGrid = new GridSquare[rows][cols];

      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          SquareType type = SquareType.values()[scanner.nextInt()];

          processSquareType(type, row, col);
        }
      }

      generatePath(spawnSquare);

      scanner.close();
    } catch (Exception exception) {
      GameScreen.lgr.error("Level input file not correct format", exception);
    }
  }

  private void processSquareType(SquareType type, int row, int col) {
    switch (type) {
      case TOWERSQUARE:
        mapGrid[row][col] = new TowerSquare(col, row, this);
        break;
      case PATHSQUARE:
        mapGrid[row][col] = new PathSquare(col, row, this);
        break;
      case SPAWNSQUARE:
        spawnSquare = new PathSquare(col, row, this);
        mapGrid[row][col] = spawnSquare;
        break;
      default:
        // Error or ignore
        break;
    }
  }

  private void generatePath(PathSquare square) {
    for (int col = -1; col <= 1; col++) {
      for (int row = -1; row <= 1; row++) {
        if ((col + row) == -1 || (col + row) == 1) {
          checkPath(square, square.getRow() + row, square.getCol() + col);
        }
      }
    }
  }

  private void checkPath(PathSquare pathSquare, int row, int col) {
    if (row >= 0 && col >= 0 && row < rows && col < cols) {
      GridSquare square = getSquare(row, col);
      if (square instanceof PathSquare && ((PathSquare) square).getNextSquare() == null) {
        PathSquare nextSquare = (PathSquare) getSquare(row, col);
        pathSquare.setNextSquare(nextSquare);
        generatePath(nextSquare);
      }
    }
  }

  /**
   * Checks if square can hold tower, and places tower.
   * 
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

  /**
   * Checks if square can hold a tower.
   * 
   * @param square Square to check
   * @return Returns whether the square can hold a tower
   */
  public boolean checkAvailable(GridSquare square) {
    return square instanceof TowerSquare && !((TowerSquare) square).hasTower();
  }
}

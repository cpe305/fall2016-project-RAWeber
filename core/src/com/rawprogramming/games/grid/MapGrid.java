package com.rawprogramming.games.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.towers.Tower;

import java.util.Scanner;

/**
 * Class representing the grid to place towers on.
 * 
 * @author Robert
 *
 */
public class MapGrid extends Grid {

  private static MapGrid instance;

  private PathSquare spawnSquare;
  private GridSquare selectedSquare;

  private enum SquareType {
    EMPTYSQUARE, TOWERSQUARE, PATHSQUARE, SPAWNSQUARE
  }

  private MapGrid() {
    super(GridSquare.SIZE /2, GridSquare.SIZE / 2);
    String file = "Level1.level";

    try {
      Scanner scanner = new Scanner(Gdx.files.internal(file).reader());

      this.rows = scanner.nextInt();
      this.cols = scanner.nextInt();

      gridMap = new GridSquare[rows][cols];

      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          SquareType type = SquareType.values()[scanner.nextInt()];

          processSquareType(type, row, col);
        }
      }

      generatePath(spawnSquare);

      scanner.close();
    } catch (Exception exception) {
      Gdx.app.error("MapGrid", "Level input file not correct format", exception);
    }
  }

  private void processSquareType(SquareType type, int row, int col) {
    switch (type) {
      case EMPTYSQUARE:
        gridMap[row][col] = new EmptySquare(col, row, this);
        break;
      case TOWERSQUARE:
        gridMap[row][col] = new TowerSquare(col, row, this);
        break;
      case PATHSQUARE:
        gridMap[row][col] = new PathSquare(col, row, this);
        break;
      case SPAWNSQUARE:
        spawnSquare = new PathSquare(col, row, this);
        gridMap[row][col] = spawnSquare;
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

  public GridSquare getSelectedSquare() {
    return selectedSquare;
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

  @Override
  public void render() {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        GridSquare square = gridMap[row][col];
        GameApp.getSpritebatch().draw(square.getTile(), square.getCoordX(), square.getCoordY(),
            GridSquare.SIZE, GridSquare.SIZE);
        if (square == selectedSquare) {
          GameApp.getSpritebatch().draw(
              GameApp.getAssetManager().get("SelectedTower.png", Texture.class), square.getCoordX(),
              square.getCoordY(), GridSquare.SIZE, GridSquare.SIZE);
        }
      }
    }
  }

  /**
   * Get instance of MapGrid using singleton pattern.
   * 
   * @return Instance of MapGrid
   */
  public static MapGrid getInstance() {
    if (instance == null) {
      instance = new MapGrid();
    }
    return instance;
  }
  
  public static void clear() {
    instance = null;
  }
}

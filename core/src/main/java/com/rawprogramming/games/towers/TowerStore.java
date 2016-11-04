package com.rawprogramming.games.towers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.StoreGrid;
import com.rawprogramming.games.grid.TowerSquare;

import java.util.ArrayList;

public class TowerStore {

  private static final int STORE_COLS = 4;
  private static final int STORE_ROWS = 1;

  private int money;
  private boolean isEnabled;
  private ArrayList<Tower> towers;
  private Tower selectedTower;
  private StoreGrid grid;
  
  private BitmapFont font;

  /**
   * Constructor for TowerStore.
   * 
   * @param money Amount of money player initially has
   * @param offsetX Offset of towerstore along x axis
   * @param offsetY Offset of towerstore along y axis
   */
  public TowerStore(int money, int offsetX, int offsetY) {
    this.grid = new StoreGrid(STORE_ROWS, STORE_COLS, offsetX, offsetY);
    this.towers = new ArrayList<Tower>();
    generateTowers();
    this.money = money;
    this.isEnabled = false;
    
    this.font = new BitmapFont(true);
    this.font.getData().setScale(1.5f);
  }

  public void selectTower(float coordX, float coordY) {
    TowerSquare square = grid.getTouchedSquare(coordX, coordY);
    selectedTower = square.getTower();
  }

  public void deselectTower() {
    selectedTower = null;
  }

  public Tower buyTower() {
    if (selectedTower != null && money >= selectedTower.getCost()) {
      money -= selectedTower.getCost();
      return selectedTower;
    }
    return null;
  }

  /**
   * Generates tower store towers.
   */
  public void generateTowers() {
    towers.add(new Tower("BasicTower", 50));

    for (int i = 0; i < STORE_ROWS; i++) {
      for (int j = 0; j < STORE_COLS; j++) {
        if (i * STORE_COLS + j < towers.size()) {
          grid.getSquare(i, j).setTower(towers.get(i * STORE_COLS + j));
        }
      }
    }
  }

  public boolean hasTowerSelected() {
    return selectedTower != null;
  }

  public void addMoney(int money) {
    this.money += money;
  }

  public StoreGrid getGrid() {
    return grid;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void toggleEnabled() {
    isEnabled = !isEnabled;
  }

  public void render() {
    if (isEnabled) {
      grid.render();
    }

    font.draw(GameApp.batch, "Money: " + money, 10, 10);

    /*
     * if (hasTowerSelected()) { GameApp.batch.draw(selectedTower.getSprite(), Gdx.input.getX() -
     * GridSquare.SquareSize / 2, Gdx.input.getY() - GridSquare.SquareSize / 2,
     * GridSquare.SquareSize, GridSquare.SquareSize); }
     */
  }
}

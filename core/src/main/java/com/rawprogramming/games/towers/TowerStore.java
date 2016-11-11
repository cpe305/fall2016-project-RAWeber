package com.rawprogramming.games.towers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.StoreGrid;
import com.rawprogramming.games.grid.TowerSquare;

import java.util.ArrayList;

/**
 * Store to buy towers from in game.
 * 
 * @author Robert
 *
 */
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

  /**
   * Selects a tower from the store.
   * 
   * @param coordX X coordinate of tower to select
   * @param coordY Y coordinate of tower to select
   */
  public void selectTower(float coordX, float coordY) {
    TowerSquare square = grid.getTouchedSquare(coordX, coordY);
    selectedTower = square.getTower();
  }

  /**
   * Buys the selected tower.
   * 
   * @return Returns the bought tower
   */
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

  /**
   * Adds money to the players bank.
   * 
   * @param money The amount to add to the bank
   */
  public void addMoney(int money) {
    this.money += money;
  }

  public StoreGrid getGrid() {
    return grid;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  /**
   * Toggles whether the store is visible.
   */
  public void toggleEnabled() {
    isEnabled = !isEnabled;
  }

  /**
   * Renders the tower store.
   */
  public void render() {
    if (isEnabled) {
      grid.render();
    }

    font.draw(GameApp.getSpritebatch(), "Money: " + money, 10, 10);
  }
}

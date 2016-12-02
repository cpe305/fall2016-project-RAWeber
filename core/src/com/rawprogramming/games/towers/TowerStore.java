package com.rawprogramming.games.towers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.StoreGrid;

import java.util.ArrayList;

/**
 * Store to buy towers from in game.
 * 
 * @author Robert
 *
 */
public class TowerStore {

  private static TowerStore instance;

  private static final int STORE_COLS = 4;
  private static final int STORE_ROWS = 1;

  private int money;
  private boolean isEnabled;
  private ArrayList<Tower> towers;
  private ArrayList<Tower> boughtTowers;
  private Tower selectedTower;
  private StoreGrid grid;

  private BitmapFont font;

  private TowerStore() {
    this.money = 250;
    this.isEnabled = false;

    this.grid = new StoreGrid(STORE_ROWS, STORE_COLS, 0, 0);
    this.towers = new ArrayList<Tower>();
    this.boughtTowers = new ArrayList<Tower>();
    generateTowers();

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
    selectedTower = grid.getTouchedSquare(coordX, coordY).getTower();
  }

  /**
   * Buys the selected tower.
   * 
   * @return Returns the bought tower
   */
  public Tower buyTower() {
    if (selectedTower != null && money >= selectedTower.getCost()) {
      money -= selectedTower.getCost();
      Tower newTower = new Tower(selectedTower);
      boughtTowers.add(newTower);
      return newTower;
    }
    return null;
  }

  /**
   * Generates tower store towers.
   */
  public void generateTowers() {
    towers.add(new Tower("BasicTower", 100, new ProjectileAttack(5, 1.5f, 1f)));
    towers.add(new Tower("AreaTower", 150, new AreaAttack(5, 1, 1f)));
    towers.add(new Tower("StrongTower", 300, new ProjectileAttack(10, 2, 0.75f)));
    towers.add(new Tower("StrongAreaTower", 350, new AreaAttack(25, 1.5f, 2f)));

    for (int i = 0; i < STORE_ROWS; i++) {
      for (int j = 0; j < STORE_COLS; j++) {
        if (i * STORE_COLS + j < towers.size()) {
          grid.getSquare(i, j).setTower(towers.get(i * STORE_COLS + j));
        }
      }
    }
  }

  public int getMoney() {
    return money;
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

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  /**
   * Renders the tower store.
   */
  public void render() {
    for (Tower tower : boughtTowers) {
      tower.render();
    }

    if (isEnabled) {
      grid.render();
    }

    font.draw(GameApp.getSpritebatch(), "Money: " + money, 30, 10);
  }

  /**
   * Get instance of TowerStore using singleton pattern.
   * 
   * @return Instance of TowerStore
   */
  public static TowerStore getInstance() {
    if (instance == null) {
      instance = new TowerStore();
    }
    return instance;
  }

  /**
   * Clears singleton instance for new game.
   */
  public static void clear() {
    instance = null;
  }
}

package com.rawprogramming.games.towers;

import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.Grid;
import com.rawprogramming.games.GridSquare;

import java.util.ArrayList;

public class TowerStore extends Grid {

  private int money;
  private ArrayList<Tower> towers;
  private Tower selectedTower;

  /**
   * Constructor for TowerStore.
   * @param money Amount of money player initially has
   * @param offsetX Offset of towerstore along x axis
   * @param offsetY Offset of towerstore along y axis
   * @param game Reference to GameApp
   */
  public TowerStore(int money, int offsetX, int offsetY, GameApp game) {
    super(2, 5, offsetX, offsetY, game);
    towers = new ArrayList<Tower>();
    generateTowers();
    this.money = money;
  }

  public void selectTower(float coordX, float coordY) {
    GridSquare square = getTouchedSquare(coordX, coordY);
    selectedTower = square.getTower();
  }

  public void deselectTower() {
    selectedTower = null;
  }

  public Tower buyTower() {
    money -= selectedTower.getCost();
    return selectedTower;
  }

  /**
   * Generates tower store towers.
   */
  public void generateTowers() {
    towers.add(new Tower("Basic Tower", 50));
    for (int i = 0; i < towers.size(); i++) {
      if (i % 2 == 0) {
        getSquare(1, i / 2).setTower(towers.get(i));
      } else {
        getSquare(0, (int) Math.ceil(i / 2.0)).setTower(towers.get(i));
      }
    }
  }

  public boolean hasTowerSelected() {
    return selectedTower != null;
  }

  public void addMoney(int money) {
    this.money += money;
  }
}

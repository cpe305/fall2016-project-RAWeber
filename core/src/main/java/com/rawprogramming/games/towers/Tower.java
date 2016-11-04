package com.rawprogramming.games.towers;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

public class Tower {

  private String name;
  private int cost;
  private int kills;
  private Texture sprite;

  private TowerUpgrade upgrade1;
  private TowerUpgrade upgrade2;

  private TowerAttack attack;

  public Tower(String name, int cost) {
    this.name = name;
    this.cost = cost;
    this.sprite = GameApp.manager.get(this.name + ".png", Texture.class);
  }

  /**
   * Constructor for Tower.
   * 
   * @param name Name of tower
   * @param cost Cost of tower
   * @param attack Attack type of tower
   * @param upgrade1 First upgrade for tower
   * @param upgrade2 Second upgrade for tower
   */
  public Tower(String name, int cost, TowerAttack attack, TowerUpgrade upgrade1,
      TowerUpgrade upgrade2) {
    this.name = name;
    this.cost = cost;
    this.attack = attack;
    this.upgrade1 = upgrade1;
    this.upgrade2 = upgrade2;
  }

  public void attackTargets() {
    attack.attackTargets();
  }

  public void upgrade1(int value) {
    upgrade1.upgrade(this, value);
  }

  public void upgrade2(int value) {
    upgrade2.upgrade(this, value);
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public Texture getSprite() {
    return sprite;
  }
}

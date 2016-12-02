package com.rawprogramming.games.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.GridSquare;

/**
 * Class representing a tower.
 * 
 * @author Robert
 *
 */
public class Tower {

  private String name;
  private int cost;
  private Vector2 position;
  private Texture sprite;

  private TowerUpgrade upgrade1;
  private TowerUpgrade upgrade2;

  private TowerAttack attack;
  private long currTime;
  private boolean active;

  /**
   * Test constructor for a Tower.
   * 
   * @param name Name of the tower
   * @param cost Cost of the tower
   * @param attack Attack tower uses
   */
  public Tower(String name, int cost, TowerAttack attack) {
    this.name = name;
    this.cost = cost;
    this.attack = attack;
    this.active = false;
    this.sprite = GameApp.getAssetManager().get(this.name + ".png", Texture.class);
  }

  /**
   * Constructor to make tower copies.
   * 
   * @param template Tower to copy
   */
  public Tower(Tower template) {
    this.name = template.getName();
    this.cost = template.getCost();
    this.position = template.getPosition();
    this.attack = template.getAttack();
    this.currTime = System.currentTimeMillis();
    this.active = true;
    this.sprite = GameApp.getAssetManager().get(this.name + ".png", Texture.class);
  }

  /**
   * Constructor for Tower.
   * 
   * @param name Name of tower
   * @param cost Cost of tower
   * @param position Position of tower
   * @param attack Attack type of tower
   * @param upgrade1 First upgrade for tower
   * @param upgrade2 Second upgrade for tower
   */

  public Tower(String name, int cost, Vector2 position, TowerAttack attack, TowerUpgrade upgrade1,
      TowerUpgrade upgrade2) {
    this.name = name;
    this.cost = cost;
    this.position = position;
    this.attack = attack;
    this.upgrade1 = upgrade1;
    this.upgrade2 = upgrade2;
  }

  private boolean attackTargets() {
    return attack.attackTargets(
        new Vector2(position.x + GridSquare.SIZE / 2, position.y + GridSquare.SIZE / 2));
  }

  /**
   * Upgrade the towers first stat.
   * 
   * @param value Value to upgrade
   */
  public void upgrade1(int value) {
    upgrade1.upgrade(this, value);
  }

  /**
   * Upgrade the towers second stat.
   * 
   * @param value Value to upgrade
   */
  public void upgrade2(int value) {
    upgrade2.upgrade(this, value);
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public TowerAttack getAttack() {
    return attack.getCopy();
  }

  /**
   * Renders the tower and its attacks.
   */
  public void render() {
    GameApp.getSpritebatch().draw(sprite, position.x, position.y, GridSquare.SIZE, GridSquare.SIZE);
    if (active) {
      if (currTime + attack.getAttackDelay() < System.currentTimeMillis() && attackTargets()) {

        currTime = System.currentTimeMillis();
      }
      attack.render();
    }
  }
}

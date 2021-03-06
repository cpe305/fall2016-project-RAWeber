package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.enemies.Enemy;
import com.rawprogramming.games.enemies.Spawner;

import java.util.ArrayList;

/**
 * Abstract class representing a towers attack type.
 * 
 * @author Robert Weber
 *
 */
public abstract class TowerAttack {

  protected int damage;
  protected float range;
  protected float attackDelay;

  /**
   * Constuctor for TowerAttack.
   * 
   * @param damage Damage of attack
   * @param range Range of attack
   * @param attackDelay Delay between attacks
   */
  public TowerAttack(int damage, float range, float attackDelay) {
    this.range = range;
    this.damage = damage;
    this.attackDelay = attackDelay;
  }

  protected ArrayList<Enemy> findTargetsInRange(Vector2 position) {
    ArrayList<Enemy> tempTargets = new ArrayList<Enemy>();
    ArrayList<Enemy> enemies = Spawner.getInstance().getEnemies();
    for (Enemy enemy : enemies) {
      if (position.dst(enemy.getCenter()) < range * 100) {
        tempTargets.add(enemy);
      }
    }
    return tempTargets;
  }

  protected abstract ArrayList<Enemy> findTargets(ArrayList<Enemy> tempTargets);

  /**
   * Attacks targets in range.
   * 
   * @param position Position to start attack from
   * @return Returns whether any enemies were attacked
   */
  public abstract boolean attackTargets(Vector2 position);

  /**
   * Renders the attack.
   */
  public abstract void render();

  public int getAttackDelay() {
    return (int) (attackDelay * 1000);
  }

  public abstract TowerAttack getCopy();
}

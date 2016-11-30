package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.enemies.Enemy;
import com.rawprogramming.games.enemies.Spawner;

import java.util.ArrayList;

public abstract class TowerAttack {

  protected int damage;
  protected float range;
  private float attackDelay;

  /**
   * Constuctor for TowerAttack.
   * 
   * @param damage Damage of attack
   * @param range Range of attack
   * @param attackDelay Delay between attacks
   */
  public TowerAttack(int damage, float range, float attackDelay) {
    this.range = range * 100;
    this.damage = damage;
    this.attackDelay = attackDelay * 1000;
  }

  protected ArrayList<Enemy> findTargetsInRange(Vector2 position) {
    ArrayList<Enemy> tempTargets = new ArrayList<Enemy>();
    ArrayList<Enemy> enemies = Spawner.getInstance().getEnemies();
    for (Enemy enemy : enemies) {
      if (position.dst(enemy.getCenter()) < range) {
        tempTargets.add(enemy);
      }
    }
    return tempTargets;
  }

  protected abstract ArrayList<Enemy> findTargets(ArrayList<Enemy> tempTargets);

  public abstract void attackTargets(Vector2 position);

  public abstract void render();

  public int getAttackDelay() {
    return (int)attackDelay;
  }
}

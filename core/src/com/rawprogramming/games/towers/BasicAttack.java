package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.enemies.Enemy;

import java.util.ArrayList;

public class BasicAttack extends TowerAttack {

  private ArrayList<Projectile> projectiles;
  private ArrayList<Projectile> projectilesToRemove;

  /**
   * Constructor for BasicAttack.
   * 
   * @param damage Damage of attack
   * @param range Range of attack
   * @param attackDelay Delay between attacks
   */
  public BasicAttack(int damage, float range, float attackDelay) {
    super(damage, range, attackDelay);
    projectiles = new ArrayList<Projectile>();
    projectilesToRemove = new ArrayList<Projectile>();
  }

  @Override
  public void attackTargets(Vector2 position) {
    ArrayList<Enemy> tempTargets = findTargetsInRange(position);
    ArrayList<Enemy> targets = findTargets(tempTargets);

    for (Enemy enemy : targets) {
      projectiles.add(new Projectile(position, enemy, damage, 2, 200));
    }
  }

  @Override
  protected ArrayList<Enemy> findTargets(ArrayList<Enemy> tempTargets) {
    ArrayList<Enemy> targets = new ArrayList<Enemy>();
    if (!tempTargets.isEmpty()) {
      Enemy target = tempTargets.get(0);
      for (Enemy enemy : tempTargets) {
        if (enemy.getDistanceTraveled() > target.getDistanceTraveled()) {
          target = enemy;
        }
      }
      targets.add(target);
    }
    return targets;
  }

  @Override
  public void render() {
    for (Projectile projectile : projectiles) {
      if (projectile.hasImpacted()) {
        projectilesToRemove.add(projectile);
      } else {
        projectile.render();
      }
    }
    projectiles.remove(projectilesToRemove);
    projectilesToRemove.clear();
  }
}

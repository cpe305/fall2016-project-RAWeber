package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.enemies.Enemy;

import java.util.ArrayList;
import java.util.Iterator;

public class ProjectileAttack extends TowerAttack {

  private ArrayList<Projectile> projectiles;

  /**
   * Constructor for BasicAttack.
   * 
   * @param damage Damage of attack
   * @param range Range of attack
   * @param attackDelay Delay between attacks
   */
  public ProjectileAttack(int damage, float range, float attackDelay) {
    super(damage, range, attackDelay);
    projectiles = new ArrayList<Projectile>();
  }

  @Override
  public boolean attackTargets(Vector2 position) {
    ArrayList<Enemy> tempTargets = findTargetsInRange(position);
    ArrayList<Enemy> targets = findTargets(tempTargets);

    for (Enemy enemy : targets) {
      projectiles.add(new Projectile(position, enemy, damage, 2, 200));
    }
    
    return !targets.isEmpty();
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
    Iterator<Projectile> iter = projectiles.iterator();
    while(iter.hasNext()){
      Projectile projectile = iter.next();
      if (projectile.hasImpacted()) {
        iter.remove();
      } else {
        projectile.render();
      }
    }
  }
  
  @Override
  public TowerAttack getCopy(){
    return new ProjectileAttack(damage, range, attackDelay);
  }
}

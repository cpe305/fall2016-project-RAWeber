package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.enemies.Enemy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class representing a tower attack that damages enemies around it.
 * 
 * @author Robert Weber
 *
 */
public class AreaAttack extends TowerAttack {

  private ArrayList<Explosion> explosions;

  /**
   * Constructor for AreaAttack.
   * 
   * @param damage Damage of attack
   * @param range Range of attack
   * @param attackDelay Delay between attacks
   */
  public AreaAttack(int damage, float range, float attackDelay) {
    super(damage, range, attackDelay);
    explosions = new ArrayList<Explosion>();
  }

  @Override
  public boolean attackTargets(Vector2 position) {
    ArrayList<Enemy> tempTargets = findTargetsInRange(position);
    ArrayList<Enemy> targets = findTargets(tempTargets);
    if (!targets.isEmpty()) {
      Explosion explosion = new Explosion(position, damage);
      explosions.add(explosion);
      explosion.explode(targets);
    }
    return !targets.isEmpty();
  }

  @Override
  protected ArrayList<Enemy> findTargets(ArrayList<Enemy> tempTargets) {
    return tempTargets;
  }

  @Override
  public void render() {
    Iterator<Explosion> iter = explosions.iterator();
    while (iter.hasNext()) {
      Explosion explosion = iter.next();
      if (explosion.isFinished()) {
        iter.remove();
      } else {
        explosion.render();
      }
    }
  }

  @Override
  public AreaAttack getCopy() {
    return new AreaAttack(damage, range, attackDelay);
  }
}

package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.enemies.Enemy;

import java.util.ArrayList;

public class AreaAttack extends TowerAttack {

  private ArrayList<Explosion> explosions;
  private ArrayList<Explosion> explosionsToRemove;

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
    explosionsToRemove = new ArrayList<Explosion>();
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
    for (Explosion explosion : explosions) {
      if (explosion.isFinished()) {
        explosionsToRemove.add(explosion);
      } else {
        explosion.render();
      }
    }
    explosions.remove(explosionsToRemove);
    explosionsToRemove.clear();
  }
}

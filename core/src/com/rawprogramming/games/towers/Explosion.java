package com.rawprogramming.games.towers;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.Animator;
import com.rawprogramming.games.enemies.Enemy;
import com.rawprogramming.games.grid.GridSquare;

import java.util.ArrayList;

/**
 * Class representing an explosion attack.
 * 
 * @author Robert Weber
 *
 */
public class Explosion {

  private Animator animation;
  private Vector2 position;
  private int damage;

  /**
   * Constructor for the Explosion.
   * 
   * @param position Position to explode around
   * @param damage Damage to deal to targets
   */
  public Explosion(Vector2 position, int damage) {
    this.position = position;
    this.damage = damage;

    animation = new Animator("Explosion.png", new Vector2(), 2, 2, 16, false, GridSquare.SIZE,
        GridSquare.SIZE, 0);
  }

  /**
   * Damages the explosions targets.
   * 
   * @param targets Enemies to be damaged
   */
  public void explode(ArrayList<Enemy> targets) {
    for (Enemy enemy : targets) {
      enemy.takeHit(damage);
    }
  }

  public boolean isFinished() {
    return animation.isFinished();
  }

  /**
   * Renders the explosions around a point.
   */
  public void render() {
    for (int col = -1; col <= 1; col++) {
      for (int row = -1; row <= 1; row++) {
        if (col != 0 && row != 0) {
          animation.setPosition(new Vector2(col * GridSquare.SIZE - GridSquare.SIZE / 2,
              row * GridSquare.SIZE - GridSquare.SIZE / 2).add(position));
          animation.render();
        }
      }
    }
  }
}

package com.rawprogramming.games.towers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.Animator;
import com.rawprogramming.games.enemies.Enemy;
import com.rawprogramming.games.grid.GridSquare;

public class Explosion {

  private Animator animation;
  private Vector2 position;
  private int damage;

  public Explosion(Vector2 position, int damage) {
    this.position = position;
    this.damage = damage;

    animation = new Animator("Explosion.png", new Vector2(), 2, 2, 16, false, GridSquare.SIZE,
        GridSquare.SIZE, 0);
  }

  public void explode(ArrayList<Enemy> targets) {
    for (Enemy enemy : targets) {
      enemy.takeHit(damage);
    }
  }

  public boolean isFinished() {
    return animation.isFinished();
  }

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

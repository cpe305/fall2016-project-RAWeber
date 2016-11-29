package com.rawprogramming.games.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.Animator;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.GridSquare;
import com.rawprogramming.games.grid.PathSquare;
import com.rawprogramming.games.towers.TowerStore;

/**
 * Base enemy class to be spawned.
 * 
 * @author Robert
 *
 */
public class Enemy {

  private String name;
  private int health;
  private Vector2 position;
  private Rectangle hitBox;
  private float speed;
  private int reward;
  private int rotation;
  private PathSquare destination;
  private boolean isDead;
  private float distanceTraveled;
  private Animator animation;

  /**
   * Constructor for Enemy.
   * 
   * @param name Name of enemy
   * @param health Starting health of enemy
   * @param speed Speed of enemy
   * @param reward Reward for killing enemy
   * @param spawnSquare Square to spawn enemy on
   */
  public Enemy(String name, int health, float speed, int reward, PathSquare spawnSquare) {
    this.name = name;
    this.health = health;
    this.speed = speed * GridSquare.SIZE;
    this.reward = reward;
    this.destination = spawnSquare.getNextSquare();
    this.isDead = false;
    this.position = new Vector2(spawnSquare.getCoordX(), spawnSquare.getCoordY());
    this.hitBox = new Rectangle(spawnSquare.getCoordX(), spawnSquare.getCoordY(), GridSquare.SIZE,
        GridSquare.SIZE);
    this.distanceTraveled = 0;
    this.animation = new Animator("BasicEnemy.png", position, 2, 1, 4, true, GridSquare.SIZE,
        GridSquare.SIZE, 0);
  }

  /**
   * Removes health from the enemy, and checks if it has died.
   * 
   * @param damage Amount to remove from enemies health
   */
  public void takeHit(int damage) {
    this.health -= damage;
    if (health <= 0) {
      isDead = true;
      TowerStore.getInstance().addMoney(reward);
    }
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public double getSpeed() {
    return speed;
  }

  public int getReward() {
    return reward;
  }

  private int getX() {
    return (int) position.x;
  }

  private int getY() {
    return (int) position.y;
  }

  public Vector2 getCenter() {
    return hitBox.getCenter(new Vector2());
  }

  public float getDistanceTraveled() {
    return distanceTraveled;
  }

  public boolean isDead() {
    return isDead;
  }

  public Rectangle getHitBox() {
    return hitBox;
  }

  /**
   * Renders enemy on screen.
   */
  public void render() {

    if (destination != null) {
      Vector2 direction = destination.getPosition().sub(position).nor();
      rotation = (int) direction.angle() - 90;
      move(direction.scl(speed * Gdx.graphics.getDeltaTime()));
      updateDestination();
    } else {
      isDead = true;
    }
    animation.setPosition(position);
    animation.setRotation(rotation);
    animation.render();
  }

  private void updateDestination() {
    // checks to see if destination is reached
    if (destination.getCoordX() == getX() && destination.getCoordY() == getY()) {
      destination = destination.getNextSquare();
    }
  }

  private void move(Vector2 vector) {
    distanceTraveled += vector.len2();
    position.add(vector);
    hitBox.setPosition(position);
    if (vector.x > 0) {
      if (position.x > destination.getCoordX()) {
        position = destination.getPosition();
      }
    } else if (vector.x < 0) {
      if (position.x < destination.getCoordX()) {
        position = destination.getPosition();
      }
    } else if (vector.y < 0) {
      if (position.y < destination.getCoordY()) {
        position = destination.getPosition();
      }
    } else if (vector.y > 0) {
      if (position.y > destination.getCoordY()) {
        position = destination.getPosition();
      }
    }
  }
}

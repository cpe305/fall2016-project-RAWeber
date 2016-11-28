package com.rawprogramming.games.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.GridSquare;
import com.rawprogramming.games.grid.PathSquare;
import com.rawprogramming.games.grid.StoreGrid;
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

  private static final int FRAME_COLS = 2;
  private static final int FRAME_ROWS = 1;

  private Animation walkAnimation;
  private Texture walkSheet;
  private TextureRegion[] walkFrames;
  private TextureRegion currentFrame;

  private float stateTime;

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
    isDead = false;
    position = new Vector2(spawnSquare.getCoordX(), spawnSquare.getCoordY());
    hitBox = new Rectangle(spawnSquare.getCoordX(), spawnSquare.getCoordY(), GridSquare.SIZE,
        GridSquare.SIZE);
    distanceTraveled = 0;

    walkSheet = GameApp.getAssetManager().get(name + ".png", Texture.class);
    TextureRegion[][] tmp = TextureRegion.split(walkSheet, 32, 32);
    walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

    int index = 0;
    for (int i = 0; i < FRAME_ROWS; i++) {
      for (int j = 0; j < FRAME_COLS; j++) {
        walkFrames[index++] = tmp[i][j];
      }
    }
    walkAnimation = new Animation(0.25f, walkFrames);
    stateTime = 0f;
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

    stateTime += Gdx.graphics.getDeltaTime();
    currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    GameApp.getSpritebatch().draw(currentFrame, getX(), getY(), GridSquare.SIZE / 2.0f,
        GridSquare.SIZE / 2.0f, GridSquare.SIZE, GridSquare.SIZE, 1, 1, rotation);
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

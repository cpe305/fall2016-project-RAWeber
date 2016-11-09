package com.rawprogramming.games.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.GridSquare;
import com.rawprogramming.games.grid.PathSquare;

public class Enemy {

  private String name;
  private int health;
  private Vector2 center;
  private float speed;
  private int reward;
  private int rotation;
  private PathSquare destination;
  private boolean isDead;

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
   * @param destination Square for enemy to move towards
   */
  public Enemy(String name, int health, float speed, int reward, PathSquare destination) {
    this.name = name;
    this.health = health;
    this.speed = speed * GridSquare.SquareSize;
    this.reward = reward;
    this.destination = destination;
    isDead = false;
    center = new Vector2(destination.getCenter());

    walkSheet = GameApp.manager.get(name + ".png", Texture.class);
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

  private void updateDestination() {
    // checks to see if destination is reached
    if (destination.getCoordX() == getX() && destination.getCoordY() == getY()) {
      destination = destination.getNextSquare();
    }
  }

  public void takeHit(int damage) {
    this.health -= damage;
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
    return (int) (center.x - GridSquare.SquareSize / 2);
  }

  private int getY() {
    return (int) (center.y - GridSquare.SquareSize / 2);
  }

  public boolean isDead() {
    return isDead;
  }

  private void move(Vector2 vector) {
    center.add(vector);
    if (vector.x > 0) {
      rotation = 270;
      if (center.x > destination.getCenter().x) {
        center = destination.getCenter();
      }
    } else if (vector.x < 0) {
      rotation = 90;
      if (center.x < destination.getCenter().x) {
        center = destination.getCenter();
      }
    } else if (vector.y < 0) {
      rotation = 180;
      if (center.y < destination.getCenter().y) {
        center = destination.getCenter();
      }
    } else if (vector.y > 0) {
      rotation = 0;
      if (center.y > destination.getCenter().y) {
        center = destination.getCenter();
      }
    }
  }

  /**
   * Renders enemy on screen.
   */
  public void render() {

    if (destination != null) {
      Vector2 direction = destination.getCenter().sub((int) center.x, (int) center.y).nor();
      move(direction.scl(speed * Gdx.graphics.getDeltaTime()));
      updateDestination();
    } else {
      isDead = true;
    }

    stateTime += Gdx.graphics.getDeltaTime();
    currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    GameApp.batch.draw(currentFrame, getX(), getY(), GridSquare.SquareSize / 2.0f,
        GridSquare.SquareSize / 2.0f, GridSquare.SquareSize, GridSquare.SquareSize, 1, 1, rotation);
  }
}

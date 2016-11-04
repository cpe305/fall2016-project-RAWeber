package com.rawprogramming.games.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.Grid;
import com.rawprogramming.games.grid.PathSquare;

public class Enemy {

  private String name;
  private int health;
  private float coordX;
  private float coordY;
  private float speed;
  private double direction;
  private int reward;
  private PathSquare destination;

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
    this.speed = speed;
    this.reward = reward;
    this.destination = destination;
    this.coordX = destination.getCenteredX();
    this.coordY = destination.getCenteredY();

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

  public void spawn() {
    //System.out.println("Spawning enemy: " + this.name);
    move();
  }

  /**
   * Moves enemy towards destination.
   */
  public void move() {
    while (destination != null) {
      moveInXDir();
      moveInYDir();
      updateDestination();
    }
  }

  private void moveInXDir() {
    if (coordX + speed * Gdx.graphics.getDeltaTime() < destination.getCenteredX()) {
      coordX += speed * Gdx.graphics.getDeltaTime() ;
      // TODO: update xPos by xComponent of speed
    } else if (coordX - speed * Gdx.graphics.getDeltaTime() > destination.getCenteredX()) {
      coordX -= speed * Gdx.graphics.getDeltaTime() ;
      // TODO: update xPos by xComponent of speed
    } else {
      coordX = destination.getCenteredX();
    }
  }

  private void moveInYDir() {
    if (coordY + speed * Gdx.graphics.getDeltaTime() < destination.getCenteredY()) {
      coordY += speed * Gdx.graphics.getDeltaTime() ;
      // TODO: update yPos by yComponent of speed
    } else if (coordY - speed * Gdx.graphics.getDeltaTime() > destination.getCenteredY()) {
      coordY -= speed * Gdx.graphics.getDeltaTime() ;
      // TODO: update yPos by yComponent of speed
    } else {
      coordY = destination.getCenteredY();
    }
  }

  private void updateDestination() {
    // checks to see if destination is reached
    if (destination.isAtCenter((int)coordX, (int)coordY)) {
      destination = destination.getNextSquare();
      // TODO update direction when destination is updated
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

  public void render() {
    stateTime += Gdx.graphics.getDeltaTime();
    currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    GameApp.batch.draw(currentFrame, coordX - Grid.tileLen / 2, coordY - Grid.tileLen / 2,
        Grid.tileLen / 2, Grid.tileLen / 2, Grid.tileLen, Grid.tileLen, 1, 1, -90);
  }
}

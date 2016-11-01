package com.rawprogramming.games.enemies;

public class Enemy {

  private String name;
  private int health;
  private double speed;
  private int reward;

  /**
   * Constructor for Enemy.
   * @param name Name of enemy
   * @param health Starting health of enemy
   * @param speed Speed of enemy
   * @param reward Reward for killing enemy
   */
  public Enemy(String name, int health, double speed, int reward) {
    this.name = name;
    this.health = health;
    this.speed = speed;
    this.reward = reward;
  }

  public void spawn() {
    System.out.println("Spawning enemy: " + this.name);
  }

  public void move() {

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
}

package com.rawprogramming.games.Enemies;

public class Enemy {

  private String name;
  private int health;
  private double speed;
  private int reward;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public int getReward() {
    return reward;
  }

  public void setReward(int reward) {
    this.reward = reward;
  }

}

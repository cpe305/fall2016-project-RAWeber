package com.rawprogramming.games.towers;

public abstract class Projectile {

  private int damage;
  private int speed;

  public abstract void moveToTarget();

  public abstract void applyDamage();
}

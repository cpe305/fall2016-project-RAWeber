package com.rawprogramming.games.Towers;

public abstract class Projectile {
	
	private int damage;
	private int speed;
	
	public abstract void moveToTarget();
	public abstract void applyDamage();
}

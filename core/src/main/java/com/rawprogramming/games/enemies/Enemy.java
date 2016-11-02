package com.rawprogramming.games.enemies;

public class Enemy {

  private String name;
  private int health;
  private int xPos;
  private int yPos;
  private double speed;
  private double direction;
  private int reward;
  private PathSquare destination;

  /**
   * Constructor for Enemy.
   * @param name Name of enemy
   * @param health Starting health of enemy
   * @param speed Speed of enemy
   * @param reward Reward for killing enemy
   */
  public Enemy(String name, int health, double speed, int reward, int xPos, int yPos, PathSquare destination) {
    this.name = name;
    this.health = health;
    this.speed = speed;
    this.reward = reward;
    this.destination = destination;
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public void spawn() {
    System.out.println("Spawning enemy: " + this.name);
  }

  public void move() {
	  while(destination != null) {
		  moveInXDir();
		  moveInYDir();
		  updateDestination();
	  }
  }
  private void moveInXDir() {
	  if(xPos + speed < destination.getCenterX()) {
		  xPos += speed;
		  //TODO: update xPos by xComponent of speed
	  }
	  else {
		  xPos = destination.getCenterX();
	  }
  }
  
  private void moveInYDir() {
	  if(yPos + speed < destination.getCenterY()) {
		  yPos += speed;
		  //TODO: update yPos by yComponent of speed
	  }
	  else {
		  yPos = destination.getCenterY();
	  }
  }
  
  private void updateDestination() {
	//checks to see if destination is reached
	  if(destination.isEqualToPosition(xPos, yPos)) {
		  destination = destination.getNextSquare();
		  //TODO update direction when destination is updated
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
}

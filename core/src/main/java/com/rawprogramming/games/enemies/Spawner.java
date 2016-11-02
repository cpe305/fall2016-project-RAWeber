package com.rawprogramming.games.enemies;

import java.util.Timer;
import java.util.TimerTask;

public class Spawner {

  private int waveNumber;
  private int enemiesPerWave;
  private int enemyCounter;
  private int enemyDelay;
  private int enemyInterval;
  private Enemy enemy;

  private Timer timer;

  /**
   * Constructor for Spawner.
   * 
   * @param enemy Enemy to Spawn
   * @param enemiesPerWave Enemies per wave.
   * @param enemyDelay Initial spawn delay
   * @param enemyInterval Time between enemy spawns
   */
  public Spawner(Enemy enemy, int enemiesPerWave, int enemyDelay, int enemyInterval) {
    this.waveNumber = 1;
    this.enemiesPerWave = enemiesPerWave;
    this.enemyCounter = 0;
    this.enemyDelay = enemyDelay;
    this.enemyInterval = enemyInterval;
    this.timer = new Timer();
    this.enemy = enemy;
  }

  /**
   * Starts the wave of enemies.
   */
  public void sendWave() {
    timer = new Timer();
    TimerTask spawnEnemy = new ScheduledSpawner(this);
    timer.scheduleAtFixedRate(spawnEnemy, enemyDelay, enemyInterval);
  }

  /**
   * Stops spawning enemies.
   */
  public void stopWave() {
    timer.cancel();
    this.waveNumber++;
    this.enemyCounter = 0;
  }

  public void incrementEnemyCounter() {
    this.enemyCounter++;
  }

  /**
   * Spawns an enemy.
   */
  public void spawnEnemy() {
    Enemy enemy =
        new Enemy(this.enemy.getName(), this.enemy.getHealth(), this.enemy.getSpeed(),
            this.enemy.getReward());
    enemy.spawn();
    this.enemyCounter++;

    System.out.println("enemyCounter: " + this.enemyCounter);

    if (this.enemyCounter >= waveNumber * enemiesPerWave) {
      // end of wave
      System.out.println("End of Wave");
      stopWave();
    }
    // new spawn is already scheduled
  }
}

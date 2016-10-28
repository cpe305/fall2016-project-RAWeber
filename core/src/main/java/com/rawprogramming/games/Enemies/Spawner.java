package com.rawprogramming.games.Enemies;

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

  public Spawner(Enemy enemy, int enemiesPerWave, int enemyDelay, int enemyInterval) {
    this.waveNumber = 1;
    this.enemiesPerWave = enemiesPerWave;
    this.enemyCounter = 0;
    this.enemyDelay = enemyDelay;
    this.enemyInterval = enemyInterval;
    this.timer = new Timer();
    this.enemy = enemy;
  }

  public void sendWave() {
    timer = new Timer();
    TimerTask spawnEnemy = new ScheduledSpawner(this);
    timer.scheduleAtFixedRate(spawnEnemy, enemyDelay, enemyInterval);
  }

  public void spawnEnemies() {

  }

  public void stopWave() {
    timer.cancel();
    this.waveNumber++;
    this.enemyCounter = 0;
  }

  public void incrementEnemyCounter() {
    this.enemyCounter++;
  }

  public void spawnEnemy() {
    Enemy e = new Enemy(this.enemy.getName(), this.enemy.getHealth(), this.enemy.getSpeed(),
        this.enemy.getReward());
    e.spawn();
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

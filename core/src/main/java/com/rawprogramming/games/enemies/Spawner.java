package com.rawprogramming.games.enemies;

import com.rawprogramming.games.grid.PathSquare;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Spawner {

  private int waveNumber;
  private int enemiesPerWave;
  private int enemyCounter;
  private int enemyDelay;
  private int enemyInterval;

  private ArrayList<Enemy> enemies;
  private ArrayList<Enemy> deadEnemies;
  private PathSquare spawnSquare;

  private Timer timer;

  /**
   * Constructor for Spawner.
   * 
   * @param enemiesPerWave Enemies per wave.
   * @param enemyDelay Initial spawn delay in seconds
   * @param enemyInterval Time between enemy spawns in seconds
   */
  public Spawner(int enemiesPerWave, int enemyDelay, int enemyInterval, PathSquare spawnSquare) {
    this.waveNumber = 1;
    this.enemiesPerWave = enemiesPerWave;
    this.enemyCounter = 0;
    this.enemyDelay = enemyDelay * 1000;
    this.enemyInterval = enemyInterval * 1000;
    this.timer = new Timer();
    this.spawnSquare = spawnSquare;
    this.enemies = new ArrayList<Enemy>();
    this.deadEnemies = new ArrayList<Enemy>();
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
  private void stopWave() {
    timer.cancel();
    this.waveNumber++;
    this.enemyCounter = 0;
  }

  /**
   * Spawns an enemy.
   */
  public void spawnEnemy() {
    Enemy enemy = new Enemy("BasicEnemy", 50, 2, 10, spawnSquare);
    enemies.add(enemy);
    this.enemyCounter++;

    if (this.enemyCounter >= waveNumber * enemiesPerWave) {
      stopWave();
    }
  }

  /**
   * Checks whether there are enemies still alive
   * @return Returns whether enemies alive.
   */
  public boolean waveComplete() {
    return enemies.size() == 0;
  }

  /**
   * Render all enemies that are currently alive.
   */
  public void render() {
    for (Enemy enemy : enemies) {
      if (enemy.isDead()) {
        deadEnemies.add(enemy);
      } else {
        enemy.render();
      }
    }
    enemies.removeAll(deadEnemies);
    deadEnemies.clear();
  }
}

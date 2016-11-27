package com.rawprogramming.games.enemies;

import com.rawprogramming.games.grid.MapGrid;
import com.rawprogramming.games.grid.PathSquare;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to spawn enemies.
 * 
 * @author Robert
 *
 */
public class Spawner {

  private static Spawner instance;

  private int waveNumber;
  private int enemiesPerWave;
  private int enemyCounter;
  private int enemyDelay;
  private int enemyInterval;

  public ArrayList<Enemy> enemies;
  private ArrayList<Enemy> deadEnemies;
  private PathSquare spawnSquare;

  private Timer timer;

  private Spawner() {
    this.waveNumber = 1;
    this.enemiesPerWave = 10;
    this.enemyCounter = 0;
    this.enemyDelay = 1000;
    this.enemyInterval = 1000;
    this.timer = new Timer();
    this.spawnSquare = MapGrid.getInstance().getSpawnSquare();
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
    Enemy enemy = new Enemy("BasicEnemy", 10, 1, 10, spawnSquare);
    enemies.add(enemy);
    this.enemyCounter++;

    if (this.enemyCounter >= waveNumber * enemiesPerWave) {
      stopWave();
    }
  }

  /**
   * Checks whether there are enemies still alive
   * 
   * @return Returns whether enemies alive.
   */
  public boolean waveComplete() {
    return enemies.isEmpty();
  }

  public ArrayList<Enemy> getEnemies() {
    return enemies;
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

  /**
   * Get instance of Spawner using singleton pattern.
   * 
   * @return Instance of Spawner
   */
  public static Spawner getInstance() {
    if (instance == null) {
      instance = new Spawner();
    }
    return instance;
  }
}

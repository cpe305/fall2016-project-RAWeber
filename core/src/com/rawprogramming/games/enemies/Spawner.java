package com.rawprogramming.games.enemies;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.MapGrid;
import com.rawprogramming.games.grid.PathSquare;

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

  public ArrayList<Enemy> spawnedEnemies;
  private ArrayList<Enemy> deadEnemies;
  private PathSquare spawnSquare;

  private Timer timer;
  private BitmapFont font;

  private Spawner() {
    this.waveNumber = 0;
    this.enemiesPerWave = 10;
    this.enemyCounter = 0;
    this.enemyDelay = 0;
    this.enemyInterval = 1000;
    this.timer = new Timer();
    this.spawnSquare = MapGrid.getInstance().getSpawnSquare();
    this.spawnedEnemies = new ArrayList<Enemy>();
    this.deadEnemies = new ArrayList<Enemy>();
    

    this.font = new BitmapFont(true);
    this.font.getData().setScale(1.5f);
  }



  /**
   * Starts the wave of enemies.
   */
  public void sendWave() {
    waveNumber++;
    
    timer = new Timer();
    TimerTask spawnEnemy = new ScheduledSpawner(this);
    timer.scheduleAtFixedRate(spawnEnemy, enemyDelay, enemyInterval);
  }

  /**
   * Stops spawning enemies.
   */
  private void stopWave() {
    timer.cancel();
    this.enemyCounter = 0;
  }

  /**
   * Spawns an enemy.
   */
  public void spawnEnemy() {
    Random rand = new Random();
    float spawnIndex = rand.nextFloat();
    Enemy enemy;

    if (spawnIndex < 0.6f) {
      enemy = new Enemy("BasicEnemy", 15 * (1 + waveNumber / 4), 1.5f, 10, spawnSquare);
    } else if (spawnIndex < 0.9) {
      enemy = new Enemy("FastEnemy", 10 * (1 + waveNumber / 4), 2f, 5, spawnSquare);
    } else {
      enemy = new Enemy("StrongEnemy", 25 * (1 + waveNumber / 4), 1f, 15, spawnSquare);
    }
    spawnedEnemies.add(enemy);
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
    return spawnedEnemies.isEmpty();
  }

  public ArrayList<Enemy> getEnemies() {
    return spawnedEnemies;
  }

  /**
   * Render all enemies that are currently alive.
   */
  public void render() {
    for (Enemy enemy : spawnedEnemies) {
      if (enemy.isDead()) {
        deadEnemies.add(enemy);
      } else {
        enemy.render();
      }
    }
    spawnedEnemies.removeAll(deadEnemies);
    deadEnemies.clear();
    
    font.draw(GameApp.getSpritebatch(), "Wave: " + waveNumber, 10, 40);
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

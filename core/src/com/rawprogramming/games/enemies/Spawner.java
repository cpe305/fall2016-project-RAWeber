package com.rawprogramming.games.enemies;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.grid.MapGrid;
import com.rawprogramming.games.grid.PathSquare;

import java.util.ArrayList;
import java.util.Random;
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
  private boolean gameOver;

  private ArrayList<Enemy> spawnedEnemies;
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
    this.gameOver = false;


    this.font = new BitmapFont(true);
    this.font.getData().setScale(1.5f);
  }



  /**
   * Starts the wave of enemies.
   */
  public void sendWave() {
    waveNumber++;

    timer = new Timer();
    TimerTask spawnEnemy = new TimerTask() {
      @Override
      public void run() {
        spawnEnemy();
      }
    };
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
      enemy = new Enemy("BasicEnemy", calculateWaveModifier(15), 1.5f, 10, spawnSquare);
    } else if (spawnIndex < 0.9) {
      enemy = new Enemy("FastEnemy", calculateWaveModifier(10), 2f, 5, spawnSquare);
    } else {
      enemy = new Enemy("StrongEnemy", calculateWaveModifier(25), 1f, 15, spawnSquare);
    }
    spawnedEnemies.add(enemy);
    this.enemyCounter++;

    if (this.enemyCounter >= calculateWaveEnemies()) {
      stopWave();
    }
  }

  private int calculateWaveModifier(int initialValue) {
    return (int) (initialValue * (1 + waveNumber / 4f));
  }

  private int calculateWaveEnemies() {
    return ((waveNumber - 1) % 5) * enemiesPerWave + enemiesPerWave;
  }

  /**
   * Checks whether there are enemies still alive
   * 
   * @return Returns whether enemies alive.
   */
  public boolean waveComplete() {
    return spawnedEnemies.isEmpty();
  }

  public int getWaveNumber() {
    return waveNumber;
  }

  public ArrayList<Enemy> getEnemies() {
    return spawnedEnemies;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Render all enemies that are currently alive.
   */
  public void render() {
    for (Enemy enemy : spawnedEnemies) {
      if (enemy.isDead()) {
        if (enemy.hasReachedEnd()) {
          gameOver = true;
        }
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

  public static void clear() {
    instance = null;
  }
}

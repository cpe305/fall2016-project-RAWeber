package com.rawprogramming.games.enemies;

import java.util.TimerTask;

/**
 * Timer task that handles spawning enemies.
 * 
 * @author Robert
 *
 */
public class ScheduledSpawner extends TimerTask {

  private Spawner spawner;

  /**
   * Constructor for the ScheduledSpawner.
   * 
   * @param spawner Reference to the spawner
   */
  public ScheduledSpawner(Spawner spawner) {
    this.spawner = spawner;
  }

  @Override
  public void run() {
    spawner.spawnEnemy();
  }
}

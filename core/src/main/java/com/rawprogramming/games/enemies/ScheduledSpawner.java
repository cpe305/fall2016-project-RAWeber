package com.rawprogramming.games.enemies;

import java.util.TimerTask;

public class ScheduledSpawner extends TimerTask {

  private Spawner spawner;

  public ScheduledSpawner(Spawner spawner) {
    this.spawner = spawner;
  }

  // Spawn an enemy
  public void run() {
    spawner.spawnEnemy();
  }
}

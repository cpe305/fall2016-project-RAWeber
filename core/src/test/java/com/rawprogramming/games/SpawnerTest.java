package com.rawprogramming.games;

import com.rawprogramming.games.Enemies.Enemy;
import com.rawprogramming.games.Enemies.Spawner;

public class SpawnerTest {
  public static void main(String args[]) {
    Spawner spawner = new Spawner(new Enemy("enemy1", 100, 1, 1), 3, 1000, 1000);
    System.out.println("Wave 1: 3 enemies per wave");
    spawner.sendWave();
  }
}

package com.rawprogramming.games.towers;

/**
 * Upgrade for a towers states.
 * 
 * @author Robert
 *
 */
public interface TowerUpgrade {
  /**
   * Upgrade the towers stats.
   * 
   * @param tower Tower to upgrade
   * @param value Value to upgrade
   */
  public void upgrade(Tower tower, int value);
}

package com.rawprogramming.games.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rawprogramming.games.GameApp;

// NOSONAR
public class DesktopLauncher {
  /**
   * Driver for desktop app.
   * 
   * @param arg Arguments for main driver
   */
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Tower Defense";
    config.width = 960;
    config.height = 540;
    new LwjglApplication(new GameApp(), config);
  }
}

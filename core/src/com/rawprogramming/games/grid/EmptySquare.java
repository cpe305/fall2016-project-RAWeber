package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

public class EmptySquare extends GridSquare{

  public EmptySquare(int col, int row, Grid grid) {
    super(col, row, grid);
    tile = GameApp.getAssetManager().get("EmptyTile.png", Texture.class);
  }

}

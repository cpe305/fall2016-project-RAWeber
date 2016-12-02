package com.rawprogramming.games.grid;

import com.badlogic.gdx.graphics.Texture;
import com.rawprogramming.games.GameApp;

/**
 * Class representing an empty GridSquare.
 * 
 * @author Robert Weber
 *
 */
public class EmptySquare extends GridSquare {

  /**
   * Constructor for EmptySquare.
   * 
   * @param col Column in grid
   * @param row Row in grid
   * @param grid Reference to the grid this is in
   */
  public EmptySquare(int col, int row, Grid grid) {
    super(col, row, grid);
    tile = GameApp.getAssetManager().get("EmptyTile.png", Texture.class);
  }

}

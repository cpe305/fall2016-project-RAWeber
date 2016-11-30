package com.rawprogramming.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.grid.GridSquare;

public class Animator {

  private Vector2 position;
  private float stateTime;
  private boolean loop;
  private Animation textureAnimation;
  private TextureRegion currentFrame;
  
  private float width;
  private float height;
  private float rotation;

  /**
   * Class to create animations.
   * 
   * @param textureName Name of texture file
   * @param position Position to render texture
   * @param cols Columns in texture sheet
   * @param rows Rows in texture sheet
   * @param frameRate Frame rate of animation
   * @param loop Whether to loop the animation
   */
  public Animator(String textureName, Vector2 position, int cols, int rows, int frameRate,
      boolean loop) {

    Texture textureSheet = GameApp.getAssetManager().get(textureName, Texture.class);
    TextureRegion[][] tmp = TextureRegion.split(textureSheet, textureSheet.getWidth() / cols,
        textureSheet.getHeight() / rows);
    TextureRegion[] frames = new TextureRegion[cols * rows];
    int index = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        frames[index++] = tmp[i][j];
      }
    }

    this.textureAnimation = new Animation(1.0f / frameRate, frames);
    this.position = position;
    this.stateTime = 0;
    this.loop = loop;
    this.width = GridSquare.SIZE;
    this.height = GridSquare.SIZE;
    this.rotation = 0;
  }

  /**
   * Class to create animations.
   * 
   * @param textureName Name of texture file
   * @param position Position to render texture
   * @param cols Columns in texture sheet
   * @param rows Rows in texture sheet
   * @param frameRate Frame rate of animation
   * @param loop Whether to loop the animation
   * @param width Scale of animation along x axis
   * @param height Scale of animation along y axis
   * @param rotation Rotation of animation
   */
  public Animator(String textureName, Vector2 position, int cols, int rows, int frameRate,
      boolean loop, float width, float height, float rotation) {

    Texture textureSheet = GameApp.getAssetManager().get(textureName, Texture.class);
    TextureRegion[][] tmp = TextureRegion.split(textureSheet, textureSheet.getWidth() / cols,
        textureSheet.getHeight() / rows);
    TextureRegion[] frames = new TextureRegion[cols * rows];
    int index = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        frames[index++] = tmp[i][j];
      }
    }

    this.textureAnimation = new Animation(1.0f / frameRate, frames);
    this.position = position;
    this.stateTime = 0;
    this.loop = loop;
    this.width = width;
    this.height = height;
    this.rotation = rotation;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }
  
  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  public boolean isFinished() {
    return textureAnimation.isAnimationFinished(stateTime);
  }

  /**
   * Renders the animation.
   */
  public void render() {
    stateTime += Gdx.graphics.getDeltaTime();
    currentFrame = textureAnimation.getKeyFrame(stateTime, loop);
    GameApp.getSpritebatch().draw(currentFrame, position.x, position.y, width / 2.0f,
        height / 2.0f, width, height, 1, 1, rotation);
  }
}

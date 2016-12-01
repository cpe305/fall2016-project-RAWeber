package com.rawprogramming.games.towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rawprogramming.games.Animator;
import com.rawprogramming.games.GameApp;
import com.rawprogramming.games.enemies.Enemy;
import com.rawprogramming.games.grid.GridSquare;

public class Projectile {

  private int damage;
  private float speed;
  private Vector2 position;
  private boolean impacted;
  private Enemy target;
  private Rectangle hitbox;
  private float rotation;
  private TextureRegion sprite;
  private Animator animation;
  private float maxDistance;

  /**
   * Constructor for Projectile.
   * 
   * @param position Position to spawn at
   * @param target Target of projectile
   * @param damage Damage of projectile
   * @param speed Speed of projectile
   */
  public Projectile(Vector2 position, Enemy target, int damage, float speed, float maxDistance) {
    this.position = new Vector2(position);
    this.target = target;
    this.damage = damage;
    this.speed = speed;
    this.maxDistance = maxDistance;
    impacted = false;
    hitbox = new Rectangle(position.x, position.y, GridSquare.SIZE / 4, GridSquare.SIZE / 4);
    sprite = new TextureRegion(GameApp.getAssetManager().get("Projectile.png", Texture.class));
  }


  private void moveToTarget() {
    Vector2 direction = new Vector2(target.getCenter()).sub(position).nor();
    rotation = direction.angle() - 90;
    direction.scl(speed * GridSquare.SIZE * Gdx.graphics.getDeltaTime());
    speed += 0.1f;
    position.add(direction);
    hitbox.setPosition(position);
    maxDistance -= direction.len();
  }

  private void applyDamage() {
    target.takeHit(damage);
    impacted = true;
    animation = new Animator("Impact.png", position, 2, 2, 16, false, GridSquare.SIZE / 2,
        GridSquare.SIZE / 2, 0);
  }

  private void destroyDud() {
    impacted = true;
    animation = new Animator("Dud.png", position, 2, 2, 16, false, GridSquare.SIZE / 2,
        GridSquare.SIZE / 2, 0);
  }

  public boolean hasImpacted() {
    return impacted && animation != null && animation.isFinished();
  }

  /**
   * Renders the projectile.
   */
  public void render() {
    if (!impacted) {
      moveToTarget();
      if (hitbox.overlaps(target.getHitBox())) {
        applyDamage();
      } else if (target.isDead() || maxDistance < 0) {
        destroyDud();
      }
      GameApp.getSpritebatch().draw(sprite, hitbox.x, hitbox.y, hitbox.width / 2, hitbox.height / 2,
          hitbox.width, hitbox.height, 1.0f, 1.0f, rotation);
    } else {
      animation.render();
    }
  }
}

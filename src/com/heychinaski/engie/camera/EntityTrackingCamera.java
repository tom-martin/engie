package com.heychinaski.engie.camera;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.heychinaski.engie.Entity;
import com.heychinaski.engie.Game;

public class EntityTrackingCamera extends Camera {
  
  private Entity toTrack;

  public EntityTrackingCamera(Entity toTrack, Game game) {
    super(game);
    this.toTrack = toTrack;
  }

  @Override
  public void update(float tick, Game game) {
    this.nextX = toTrack.x;
    this.nextY = toTrack.y;
  }

  @Override
  public void render(Graphics2D g) {}

  @Override
  public void collided(Entity with, float tick, Game game, Rectangle2D.Float bounds, Rectangle2D.Float nextBounds, Rectangle2D.Float withBounds) {
  }

}

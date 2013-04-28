package com.heychinaski.engie.camera;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.heychinaski.engie.Entity;
import com.heychinaski.engie.Game;

public class EntityTrackingCamera extends Camera {
  
  public Entity toTrack;
  
  boolean trackX = true;
  boolean trackY = true;
  
  float offsetX = 0f;
  float offsetY = 0f;

  public EntityTrackingCamera(Game game, 
      Entity toTrack, 
      boolean trackX,
      boolean trackY,
      float offsetX,
      float offsetY) {
    super(game);
    this.toTrack = toTrack;
    this.trackX = trackX;
    this.trackY = trackY;
    
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }

  public EntityTrackingCamera(Entity toTrack, 
                              Game game) {
    super(game);
    this.toTrack = toTrack;
  }

  @Override
  public void update(float tick, Game game) {
    if(trackX) this.nextX = (toTrack.x + offsetX);
    if(trackY) this.nextY = (toTrack.y + offsetY);
  }

  @Override
  public void render(Graphics2D g, Game game) {}

  @Override
  public void collided(Entity with, float tick, Game game, Rectangle2D.Float bounds, Rectangle2D.Float nextBounds, Rectangle2D.Float withBounds) {
  }

}

package com.heychinaski.engie.camera;

import java.awt.Graphics2D;

import com.heychinaski.engie.Entity;
import com.heychinaski.engie.Game;

public abstract class Camera extends Entity {

  public Game game;
  
  public float zoom = 1;

  public Camera(Game game) {
    this.game = game;
  }

  public void look(Graphics2D g) {
    g.scale(zoom, zoom);
    g.translate((game.getWidth() / (2 * zoom))-x, (game.getHeight() / (2 * zoom))-y);
  }
  
}

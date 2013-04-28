package com.heychinaski.engie.example;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D.Float;

import com.heychinaski.engie.Entity;
import com.heychinaski.engie.Game;

public class TestEntity extends Entity {

  @Override
  public void update(float tick, Game game) {
    if(game.input.isKeyDown(KeyEvent.VK_LEFT)) nextX -= (tick * 100);
    if(game.input.isKeyDown(KeyEvent.VK_RIGHT)) nextX += (tick * 100); 
    if(game.input.isKeyDown(KeyEvent.VK_UP)) nextY -= (tick * 100); 
    if(game.input.isKeyDown(KeyEvent.VK_DOWN)) nextY += (tick * 100); 
  }

  @Override
  public void render(Graphics2D g, Game game) {
    g.setColor(Color.green);
    
    g.fillRect(round(x - 20), round(y + 20), 20, 20);
  }

  @Override
  public void collided(Entity with, float tick, Game game, Float bounds,
      Float nextBounds, Float withBounds) {
    
  }

}

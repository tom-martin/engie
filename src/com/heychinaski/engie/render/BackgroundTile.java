package com.heychinaski.engie.render;

import java.awt.Graphics2D;
import java.awt.Image;

public class BackgroundTile {
  Image image;
  private int size;

  public BackgroundTile(Image image) {
    this.image = image; 
    size = image.getWidth(null);
  }
  
  public void render(int x, int y, int endX, int endY, Graphics2D g) {
    int normX = ((x / size) * size) - size;
    int normY = ((y / size) * size) - size;
    
    int normEndX = ((endX / size) * size) + size;
    int normEndY = ((endY / size) * size) + size;
    
    for(int cy = normY - size;cy < normEndY;cy+=size) {
      for(int cx = normX - size;cx < normEndX;cx+=size) { 
        g.drawImage(image, cx, cy, null);
      }
    }
  }
}

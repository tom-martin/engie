package com.heychinaski.engie;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

  private Map<String, Image> images = new HashMap<String, Image>();
  private Map<String, BufferedImage> compImages = new HashMap<String, BufferedImage>();
      
  public ImageManager(Component component, String...requiredImages) {
    MediaTracker mediaTracker = new MediaTracker(component);
    
    int nextId = 0;
    for(String imageLoc : requiredImages) {
      Image image = loadImage(imageLoc);
      mediaTracker.addImage(image, nextId);
      images.put(imageLoc, image);
      
      nextId ++;
    }
    
    for(int i = 0; i < nextId; i++) {
      try {
        mediaTracker.waitForID(i);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  private Image loadImage(String imageName) {
    ClassLoader loader = getClass().getClassLoader();
    URL url = loader.getResource(imageName);
    return Toolkit.getDefaultToolkit().getImage(url);
  }
  
  private BufferedImage toCompatibleImage(Image image) {
  	// obtain the current system graphical settings
  	GraphicsConfiguration gfx_config = GraphicsEnvironment.
  		getLocalGraphicsEnvironment().getDefaultScreenDevice().
  		getDefaultConfiguration();


  	// image is not optimized, so create a new image that is
  	BufferedImage new_image = gfx_config.createCompatibleImage(
  			image.getWidth(null), image.getHeight(null), BufferedImage.BITMASK);

  	// get the graphics context of the new image to draw the old image on
  	Graphics2D g2d = (Graphics2D) new_image.getGraphics();

  	// actually draw the image and dispose of context no longer needed
  	g2d.drawImage(image, 0, 0, null);
  	g2d.dispose();

  	// return the new optimized image
  	return new_image; 
  }
  
  public BufferedImage get(String loc) {
	if(compImages.get(loc) == null) {
		Image img = images.get(loc);
		if(img != null) {
			compImages.put(loc, toCompatibleImage(images.get(loc)));
		}
	}
    return compImages.get(loc);
  }
}

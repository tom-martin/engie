package com.heychinaski.engie.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.heychinaski.engie.Game;
import com.heychinaski.engie.camera.Camera;
import com.heychinaski.engie.camera.EntityTrackingCamera;
import com.heychinaski.engie.camera.SimpleCamera;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    JFrame mainWindow = new JFrame("Test game");
    JPanel panel = (JPanel) mainWindow.getContentPane();
    
    panel.setPreferredSize(new Dimension(1024, 768));
    panel.setLayout(new BorderLayout());
    
    final Game game = new Game() {
      
      private TestEntity testEntity;

      @Override
      public void init() {
        testEntity = new TestEntity();
        entities.add(testEntity);
      }

      @Override
      public String[] images() {
        return new String[] {};
      }

      @Override
      public Camera camera() {
        Camera camera = new EntityTrackingCamera(testEntity, this);
        camera.zoom = 1;
        return camera;
      }

      @Override
      public Image bgTileImage() {
        Image image = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.red);
        g.fillRect(0, 0, 10, 10);
        g.fillRect(10, 10, 10, 10);
        g.dispose();
        return image;
      }
      
    };
    panel.add(game);
    
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.pack();
    
    mainWindow.setCursor(mainWindow.getToolkit().createCustomCursor(
        new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
        "null"));
    
    new Thread() {
      public void run() {
        game.start();
      }
    }.start();
    
    game.requestFocus();
    mainWindow.setVisible(true);
  }

}

package com.heychinaski.engie;

import static java.lang.Math.round;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.heychinaski.engie.camera.Camera;
import com.heychinaski.engie.collision.CollisionManager;
import com.heychinaski.engie.render.BackgroundTile;

public abstract class Game extends Canvas {
  private static final long serialVersionUID = 1L;
  
  boolean running = true;
  
  protected ImageManager imageManager;
  protected Camera camera;
  protected BackgroundTile bgTile;
  
  public List<Entity> entities;

  private CollisionManager collisionManager;
  public Input input = new Input();

  public Game() {
    setIgnoreRepaint(true);
    
    addKeyListener(new KeyListener() {
      @Override public void keyTyped(KeyEvent e) {}
      @Override public void keyReleased(KeyEvent e) {input.keyUp(e.getKeyCode());}
      @Override public void keyPressed(KeyEvent e) { input.keyDown(e.getKeyCode());}
    });
    
    addMouseListener(new MouseListener() {
      @Override public void mouseClicked(MouseEvent arg0) {}
      @Override public void mouseEntered(MouseEvent arg0) {}
      @Override public void mouseExited(MouseEvent arg0) {}
      @Override public void mousePressed(MouseEvent e) { input.setMouseDown(e.getButton()); }
      @Override public void mouseReleased(MouseEvent e) { input.setMouseUp(e.getButton()); }
    });
  }
  
  public void start() {
    entities = new ArrayList<Entity>();
    init();
    imageManager = new ImageManager(this, new String[] {});
    camera = camera();
    entities.add(camera);
    collisionManager = new CollisionManager(this);
    
    Image bgTileImage = bgTileImage();
     if(bgTileImage != null) bgTile = new BackgroundTile(bgTileImage);
    
    createBufferStrategy(2);
    BufferStrategy strategy = getBufferStrategy();
    
    Graphics2D g;
    
    long last = System.currentTimeMillis();
    while (running) {
      long now = System.currentTimeMillis();
      float tick = (float)(now - last) / 1000;
      last = now;
      
      update(tick);
      
      g = (Graphics2D)strategy.getDrawGraphics();
      g = (Graphics2D) g.create();
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
      
      g.setColor(Color.black);
      g.fillRect(0, 0, getWidth(), getHeight());
      
      camera.look(g);
      
      // render
      if(bgTile != null) bgTile.render(round(camera.x-((getWidth() / 2) * (1 / camera.zoom))), 
                                       round(camera.y-((getHeight() / 2) * (1 / camera.zoom))),
                                       round(camera.x+((getWidth() / 2) * (1 / camera.zoom))),
                                       round(camera.y+((getHeight() / 2) * (1 / camera.zoom))), g);
      
      render(g);
      
      g.dispose();
      strategy.show();
      
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public abstract String[] images();
  public abstract Camera camera();
  public abstract Image bgTileImage();
  public void render(Graphics2D g) {
    for(int i = 0; i < entities.size(); i++) {
      entities.get(i).render(g);
    }
  }
  public abstract void init();
  public void update(float tick) {
    for(int i = 0; i < entities.size(); i++) {
      entities.get(i).update(tick, this);
    }
    
     collisionManager.update(tick);
    
    for(int i = 0; i < entities.size(); i++) {
      entities.get(i).applyNext();
    }
  }
  
  public Clip playSound(String soundLoc, boolean loop) {
    try {
      Clip clip = AudioSystem.getClip();
      // read audio data from whatever source (file/classloader/etc.)
      InputStream audioSrc = Game.this.getClass().getResourceAsStream(soundLoc);
      //add buffer for mark/reset support
      InputStream bufferedIn = new BufferedInputStream(audioSrc);
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
      clip.open(inputStream);
      if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
      clip.start(); 
      
      return clip;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}

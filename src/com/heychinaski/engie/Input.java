package com.heychinaski.engie;

import com.heychinaski.engie.camera.Camera;


public class Input {

  public boolean[] pressedKeys = new boolean[1024];
  public boolean[] mouseButtons = new boolean[3];
  
  public int getMouseX() {
    return mouseX;
  }

  public void setMouseX(int mouseX) {
    this.mouseX = mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }

  public void setMouseY(int mouseY) {
    this.mouseY = mouseY;
  }

  public int getWorldMouseX() {
    return worldMouseX;
  }

  public void setWorldMouseX(int worldMouseX) {
    this.worldMouseX = worldMouseX;
  }

  public int getWorldMouseY() {
    return worldMouseY;
  }

  public void setWorldMouseY(int worldMouseY) {
    this.worldMouseY = worldMouseY;
  }

  int mouseX;
  int mouseY;
  
  int worldMouseX;
  int worldMouseY;
  
  public void keyDown(int code) {
    if(code < pressedKeys.length) pressedKeys[code] = true;
  }
  
  public void keyUp(int code) {
    if(code < pressedKeys.length) pressedKeys[code] = false;
  }
  
  public boolean isKeyDown(int code) {
    return code < pressedKeys.length ? pressedKeys[code] : false; 
  }
  
  public void update(Camera camera, int mouseX, int mouseY) {
    this.mouseX = mouseX;
    this.mouseY = mouseY;
    
    this.worldMouseX = mouseX + ((int)camera.x - (camera.game.getWidth() / 2));
    this.worldMouseY = mouseY + ((int)camera.y - (camera.game.getHeight() / 2));
  }

  public void setMouseDown(int button) {
    if(button < mouseButtons.length) {
      mouseButtons[button] = true;
    }
  }

  public void setMouseUp(int button) {
    if(button < mouseButtons.length) {
      mouseButtons[button] = false;
    }
  }
  
  public boolean isMouseDown(int button) {
    return button < mouseButtons.length && mouseButtons[button]; 
  }
  
}
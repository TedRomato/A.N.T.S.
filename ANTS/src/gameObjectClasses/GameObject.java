package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Image;

import handlers.Camera;

public abstract class GameObject {
	Image[] sprites;
	int currentSpritePointer = 0;
	int locationPointer = 0;
	
	
	public abstract void render(Graphics g, Camera c);
	
}

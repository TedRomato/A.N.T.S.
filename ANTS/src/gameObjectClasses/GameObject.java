package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Image;

import handlers.Camera;

public abstract class GameObject {
	Image[] sprites;
	int currentSpritePointer = 0;
	int locationPointer = 0;
	
	boolean isInViewport = false;

	
	
	public abstract void render(Graphics g, Camera c);
	public abstract boolean checkIfInViewport(Camera c);
	
}

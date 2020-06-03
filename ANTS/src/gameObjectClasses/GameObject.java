package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject {
	Image[] sprites;
	int currentSpritePointer;
	abstract void render(Graphics g);
	
}

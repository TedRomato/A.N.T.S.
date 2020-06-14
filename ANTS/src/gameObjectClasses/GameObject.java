package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import handlers.Camera;
import world.Tile;


public abstract class GameObject {
	Image[] sprites;
	int currentSpritePointer = 0;
	int locationPointer = 0;
	
	boolean isInViewport = false;
	

	int imageOffsetX = 0;
	int imageOffsetY = 0;
	
	int imageHeight = 5;
	int imageWidth = 5;

	
	
	public abstract void render(Graphics2D g, Camera c);
	public abstract boolean checkIfInViewport(Camera c);
	
	public void setImageScale(double imageHeightScale , double imageWidthScale) {
		imageHeight = (int) ((double) Tile.tileSideLenght*imageHeightScale);
		imageWidth = (int) ((double) Tile.tileSideLenght*imageWidthScale);
	} 
	
	public void setImageOffset(double xOffsetMultiplier, double yOffsetMultiplier) {
		imageOffsetX = (int)((double)Tile.tileSideLenght*xOffsetMultiplier);
		imageOffsetY = (int)((double)Tile.tileSideLenght*yOffsetMultiplier);
	} 
	
	
}

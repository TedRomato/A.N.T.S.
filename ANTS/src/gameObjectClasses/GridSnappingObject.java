package gameObjectClasses;

import java.awt.Graphics;

import handlers.Camera;
import world.Grid;
import world.Tile;

public abstract class GridSnappingObject extends GameObject{
	int baseTile;
	int[][] shape;
	int[] ocupiedTiles;
	
	int imageBaseTileOffsetX = -(int)((double)Tile.tileSideLenght*5.2);
	int imageBaseTileOffsetY = -(int)((double)Tile.tileSideLenght*11.5);
	
	int imageHeight = Tile.tileSideLenght*18;
	int imageWidth = Tile.tileSideLenght*14;
	


	public GridSnappingObject() {
		
	}
	
	@Override
	public void render(Graphics g, Camera c) {
		
		g.drawImage(sprites[currentSpritePointer], 
				(baseTile - (baseTile/c.getGrid().getGridColumns())*c.getGrid().getGridColumns())*c.getTileRenderSize() - c.getX() + (int)Math.round(imageBaseTileOffsetX*((double)c.getTileRenderSize())/(double)Tile.tileSideLenght), 
				(baseTile/c.getGrid().getGridColumns())*c.getTileRenderSize() - c.getY() + (int)Math.round(imageBaseTileOffsetY*((double)c.getTileRenderSize())/(double)Tile.tileSideLenght),
				(int)Math.round((double)(imageWidth*c.getTileRenderSize())/(double)Tile.tileSideLenght), 
				(int)Math.round((double)(imageHeight*c.getTileRenderSize())/(double)Tile.tileSideLenght), null);
	}
	
	@Override
	public boolean checkIfInViewport(Camera c) {
	/*	if() {
			return true;
		}
		return false;
		*/
		return false;
	}
	
	
	public void setOccupiedTiles(Grid g) {
		ocupiedTiles = new int[shape.length];
		for(int i = 0; i < ocupiedTiles.length; i++) {
			ocupiedTiles[i] = baseTile + shape[i][0] + shape[i][1]*g.getGridColumns();
		}
		
	}
	
	//Max coord distance is -9
	//coords input : 2,1;-3,5;4,8;
	public void makeShape(String coords) {
		
		int tileAmount = getTileAmount(coords);
		
		shape = new int[tileAmount][2];

		
		boolean isNegative = false;
		int value = 0;
		int tilePointer = 0;
		
		for(int i = 0; i < coords.length();i++) {
			if(coords.charAt(i) == ';') {
				if(isNegative) {
					isNegative = false;
					shape[tilePointer][1] = -value;
				}else {
					shape[tilePointer][1] = value;
				}
				tilePointer++;
			}
			else if(coords.charAt(i) == '-') {
				isNegative = true;
			}else if(coords.charAt(i) == ',') {
				if(isNegative) {
					isNegative = false;
					shape[tilePointer][0] = -value;
				}else {
					shape[tilePointer][0] = value;
				}
			}else {
				value = Integer.parseInt(String.valueOf(coords.charAt(i)));  
			}
		}
		
		
	}
	
	public int getTileAmount(String coords) {
		int tileAmount = 0;
		for(int i = 0; i < coords.length();i++) {
			if(coords.charAt(i) == ';') {
				tileAmount++;
			}
		}
		return tileAmount;
	}
	
	public int[] getOccupiedTiles() {
		return ocupiedTiles;
	}
	
}

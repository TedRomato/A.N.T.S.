package world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gameObjectClasses.GameObject;
import handlers.Camera;
import interfacePackage.Game;


public class Tile {
	TileState tileState = TileState.HIDDEN;
	int content = -1;
	public static int tileSideLenght;
	public static int[] tilePossibleSizeRange;
	int column, row;

	//renderStuff
	Color colorUsed = Color.WHITE;
	BufferedImage image;
	int tileRenderCoordX, tileRenderCoordY;
	int tileRenderSide = tileSideLenght;
	
	public Tile(int column, int row) {
		this.column = column;
		this.row = row;
		
	}
	
	enum TileState{
		HIDDEN,
		REVEALED,
		ACTIVE
	}
	
	
	
	public void updateRender(Camera camera) {
		tileRenderSide = camera.getTileRenderSize();
		tileRenderCoordX = column*tileRenderSide - camera.getX();
		tileRenderCoordY = row*tileRenderSide - camera.getY();
	}
	
	
	public void assignContent(int i) {
		content = i;
		if(i >= 0) {
			colorUsed = Color.RED;
		}
	}
	
	
	public void render(Graphics2D g) {
		//System.out.println("TILE");
		
		if(colorUsed != Color.WHITE) {
			g.setColor(colorUsed);
			g.drawRect(tileRenderCoordX, tileRenderCoordY, tileRenderSide, tileRenderSide);
		}
		

		
		
	}
	/*TODO:
	 * render() ->
	 * tileState HIDDEN - render nothing
	 * tileState REVEALED - render tile texture
	 * tileState ACTIVE - render tile texture
	 * 
	 */ 

	
	public void printCoords() {
		System.out.println("Column : " + column + " Row : " + row);
	}
	
	public void printRenderCoords() {
		System.out.println("Column : " + tileRenderCoordX + " Row : " + tileRenderCoordY);
	}
	
	//Getters&Setters

	public int getColumn() {
		return column;
	}



	public void setColumn(int column) {
		this.column = column;
	}



	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}

	public int getTileRenderCoordX() {
		return tileRenderCoordX;
	}

	public void setTileRenderCoordX(int tileRenderCoordX) {
		this.tileRenderCoordX = tileRenderCoordX;
	}

	public int getTileRenderCoordY() {
		return tileRenderCoordY;
	}

	public void setTileRenderCoordY(int tileRenderCoordY) {
		this.tileRenderCoordY = tileRenderCoordY;
	}

	public int getTileRenderSide() {
		return tileRenderSide;
	}

	public void setTileRenderSide(int tileRenderSide) {
		this.tileRenderSide = tileRenderSide;
	}

	

	
}

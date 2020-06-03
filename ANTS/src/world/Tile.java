package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameObjectClasses.GameObject;
import handlers.Camera;


public class Tile {
	TileState tileState = TileState.HIDDEN;
	GameObject[] contents = new GameObject[1];
	final static int tileSideLenght = 10;
	int column, row;

	//renderStuff
	BufferedImage image;
	int tileRenderCoordX, tileRenderCoordY;
	
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
		tileRenderCoordX = column*tileSideLenght - camera.getX();
		tileRenderCoordY = row*tileSideLenght - camera.getY();
	}
	
	public void render(Graphics g) {
		g.drawRect(tileRenderCoordX, tileRenderCoordY, tileSideLenght, tileSideLenght);
	}
	/*TODO:
	 * render() ->
	 * tileState HIDDEN - render nothing
	 * tileState REVEALED - render tile texture
	 * tileState ACTIVE - render tile texture
	 * 
	 */ 


	
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



	
}

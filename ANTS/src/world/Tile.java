package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gameObjectClasses.GameObject;
import handlers.Camera;


public class Tile {
	TileState tileState = TileState.HIDDEN;
	GameObject[] contents = new GameObject[1];
	final static int tileSideLenght = 20;
	int column, row;

	//renderStuff
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
		tileRenderCoordX = column*tileSideLenght - camera.getX();
		tileRenderCoordY = row*tileSideLenght - camera.getY();
		tileRenderSide = (int)(tileSideLenght/camera.getScale());
	}
	
	public void render(Graphics2D g) {
		g.drawRect(tileRenderCoordX, tileRenderCoordY, tileRenderSide, tileRenderSide);
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

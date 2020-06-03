package world;

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
	int tileCoordX, tileCoordY;
	
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
		tileCoordX = column*tileSideLenght - camera.getX();
		tileCoordY = row*tileSideLenght - camera.getY();
	}
	/*TODO:
	 * render() ->
	 * tileState HIDDEN - render nothing
	 * tileState REVEALED - render tile texture
	 * tileState ACTIVE - render tile texture
	 * 
	 * fillRect - (column*tileSideLenght - cameraX, row*tileSideLenght - cameraY)
	 */
	
}

package world;

import java.awt.Graphics2D;

import gameObjectClasses.GameObject;
import renderers.TileRenderer;

public class Location {
	GameObject[] objectsInLocation = new GameObject[500];
	Grid grid;
	boolean isActive = false;
	TileRenderer tileRenderer;
	
	public Location() {
		grid = new Grid(20,20);
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void addObToLocation(GameObject go){
		for(int i = 0; i < objectsInLocation.length; i++) {
			if(objectsInLocation[i] == null) {
				objectsInLocation[i] = go;
				return;
			}
		}
		System.out.println("objectsInLocation overflow");
	}
	
	public void render(Graphics2D g) {

		tileRenderer.handle(grid, g);
	}
	
}

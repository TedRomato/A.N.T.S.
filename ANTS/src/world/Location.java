package world;

import gameObjectClasses.GameObject;

public class Location {
	double scale = 1;
	GameObject[] objectsInLocation = new GameObject[500];
	Grid grid;
	
	public Grid getGrid() {
		return grid;
	}
	
}

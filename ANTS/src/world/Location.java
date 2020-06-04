package world;

import gameObjectClasses.GameObject;

public class Location {
	GameObject[] objectsInLocation = new GameObject[500];
	Grid grid;
	boolean isActive = false;
	
	
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
	
	
}

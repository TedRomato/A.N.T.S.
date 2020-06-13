package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameObjectClasses.GameObject;
import gameObjectClasses.GridSnappingObject;
import gameObjectClasses.Lake;
import gameObjectClasses.Tree;
import handlers.Animated;
import handlers.ContentArray;

//Location is set of gameObjects and grid
//Location is part of world
//You can add new Gameobjects to location, and make them 
//do stuff they are supposed to do an interact with other
//game objects

//Every location has one main array with all of its game objects
//It has several other Content Array (check out ContentArray for exp.)


public class Location {
	public GameObject[] objectsInLocation = new GameObject[500];
	public ContentArray gridSnappingObjects;
	public ContentArray animated;
	Grid grid;
	boolean isActive = false;
	public BufferedImage bg;
	
	public Location() {
		grid = new Grid(100,100);
		Tree t = new Tree(4550 ,grid);
		Tree t2 = new Tree(2780 ,grid);
		Tree t3 = new Tree(1630 ,grid);
		Tree t4 = new Tree(8020 ,grid);

		
		gridSnappingObjects = new ContentArray(10);
		animated = new ContentArray(10);
		addObToLocation(t);
		addObToLocation(t2);
		addObToLocation(t3);
		addObToLocation(t4);


		try {
			bg = ImageIO.read(new File("ANTS/src/bg/Background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateLocation() {
		updateAnimation();
	}
	
	private void updateAnimation() {
		int objectsHandled = 0;
		for(int i = 0; i < animated.getArr().length; i++) {
			if(animated.getArr()[i] != -1) {
				((Animated) objectsInLocation[animated.getArr()[i]]).updateAnimation();
				if(objectsHandled == animated.getContents()) {
					return;
				}
			}
		}
	}

	
	
	public void addObToLocation(GameObject go){
		for(int i = 0; i < objectsInLocation.length; i++) {
			if(objectsInLocation[i] == null) {
				objectsInLocation[i] = go;
				
				
				if(go instanceof GridSnappingObject) {
					gridSnappingObjects.addToHandlerArr(i);
					for(int x = 0; x < ((GridSnappingObject) go).getOccupiedTiles().length; x++) {
						grid.getTiles()[((GridSnappingObject) go).getOccupiedTiles()[x]].assignContent(i);
					}
				}
				
				if(go instanceof Animated) {
					animated.addToHandlerArr(i);
				}
				return;
			}
		}
		System.out.println("objectsInLocation overflow");
	}
	
	
	public Grid getGrid() {
		return grid;
	}
	
}

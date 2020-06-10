package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameObjectClasses.GameObject;
import gameObjectClasses.GridSnappingObject;
import gameObjectClasses.Tree;
import handlers.ContentArray;

public class Location {
	public GameObject[] objectsInLocation = new GameObject[500];
	public ContentArray gridSnappingObjects;
	Grid grid;
	boolean isActive = false;
	public BufferedImage bg;
	
	public Location() {
		grid = new Grid(100,100);
		Tree t = new Tree(4550 ,grid);
		gridSnappingObjects = new ContentArray(10);
		addObToLocation(t);
		try {
			bg = ImageIO.read(new File("ANTS/src/bg/bgTry.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Grid getGrid() {
		return grid;
	}
	
	
	public void addObToLocation(GameObject go){
		for(int i = 0; i < objectsInLocation.length; i++) {
			if(objectsInLocation[i] == null) {
				objectsInLocation[i] = go;
				if(go instanceof GridSnappingObject) {
					gridSnappingObjects.addToHandlerArr(i);
				}
				return;
			}
		}
		System.out.println("objectsInLocation overflow");
	}
	
}

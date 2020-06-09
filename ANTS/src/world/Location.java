package world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameObjectClasses.GameObject;

public class Location {
	GameObject[] objectsInLocation = new GameObject[500];
	Grid grid;
	boolean isActive = false;
	public BufferedImage bg;
	
	public Location() {
		grid = new Grid(100,100);
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
				return;
			}
		}
		System.out.println("objectsInLocation overflow");
	}
	
}

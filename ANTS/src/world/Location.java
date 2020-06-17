package world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameObjectClasses.Point;
import gameObjectClasses.Ant;
import gameObjectClasses.GameObject;
import gameObjectClasses.GridSnappingObject;
import gameObjectClasses.LivingObject;
import gameObjectClasses.Tree;
import handlers.Animated;
import handlers.ContentArray;
import interfacePackage.Game;

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
	public ContentArray objectsToRender;
	public ContentArray livingObjects;
	public ContentArray selectedObjects;
	Grid grid;
	boolean isActive = false;
	public BufferedImage bg;
	
	public Location() {
		
		
		grid = new Grid(100,100);
		Tree t = new Tree(4550 ,grid);
		Tree t2 = new Tree(2780 ,grid);
		Tree t3 = new Tree(1630 ,grid);
		Tree t4 = new Tree(8020 ,grid);
		Ant a = new Ant(new Point(100,100));
		Ant a1 = new Ant(new Point(200,500));
		Ant a2 = new Ant(new Point(600,200));



		//TODO: Make selected objects array - > make rectangle mouse selection - > only selected ants listen to new commands
		selectedObjects = new ContentArray(10);
		livingObjects = new ContentArray(10);
		gridSnappingObjects = new ContentArray(10);
		animated = new ContentArray(10);
		objectsToRender = new ContentArray(15);
		addObToLocation(t);
		addObToLocation(t2);
		addObToLocation(t3);
		addObToLocation(t4);
		addObToLocation(a);
	//	addObToLocation(a1);
	//	addObToLocation(a2);



		try {
			bg = ImageIO.read(new File("ANTS/src/bg/Background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateLocation() {
		updateAnimation();
		updateLivingObjects();
		selectLivingObjects();
		handleSelectedObjects();
	}
	/*
	private void updateGoalDestination() {
		
	}
	*/
	
	private void handleSelectedObjects() {
		if(Game.input.isRightMousePressed()) {
			int objectsHandled = 0;
			for(int i = 0; i < selectedObjects.getArr().length; i++) {
				if(selectedObjects.getArr()[i] != -1) {
					//TODO predelat do metod - nejak univerzalnejs
					((LivingObject) objectsInLocation[selectedObjects.getArr()[i]]).setNewGoalDestination(new Point(Game.input.getCursorXOnMap(),Game.input.getCursorYOnMap()));;				

					if(objectsHandled == selectedObjects.getContents()) {
						return;
					}
				}
			}
		}
	}
	
	private void selectLivingObjects() {
		if(Game.input.isSelectNow()) {
			selectedObjects.resetAray();
			Game.input.setSelectNow(false);
			
			int objectsHandled = 0;
			for(int i = 0; i < livingObjects.getArr().length; i++) {
				if(livingObjects.getArr()[i] != -1) {
					//TODO predelat do metod - nejak univerzalnejs
					if(Game.input.checkIfInsideSelectionRect(((LivingObject) objectsInLocation[livingObjects.getArr()[i]]).getColliders()))  {
						selectedObjects.addToHandlerArr(livingObjects.getArr()[i]);
					}
					if(objectsHandled == livingObjects.getContents()) {
						Game.input.restetSelectionRect();
						return;
					}
				}
			}
			Game.input.restetSelectionRect();
		}
		
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

	private void updateLivingObjects() {
		int objectsHandled = 0;
		for(int i = 0; i < livingObjects.getArr().length; i++) {
			if(livingObjects.getArr()[i] != -1) {
				((LivingObject) objectsInLocation[livingObjects.getArr()[i]]).updateLivingObject();
				if(objectsHandled == livingObjects.getContents()) {
					return;
				}
			}
		}
	}
	
	public void addObToLocation(GameObject go){
		for(int i = 0; i < objectsInLocation.length; i++) {
			if(objectsInLocation[i] == null) {
				objectsInLocation[i] = go;
				
				objectsToRender.addToHandlerArr(i);
				
				if(go instanceof LivingObject) {
					livingObjects.addToHandlerArr(i);
					}
				
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

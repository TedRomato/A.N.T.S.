package handlers;



import java.awt.Graphics;

import world.Location;
import world.Tile;

public class Camera {
	int x = 0, y = 0; 
	int width = 1920, height = 1080;
	double scale = 1; 
	Location locationObserved;
	Tile topLeftTileVisible;
	Tile botRightTileVisible;
	
	public Camera(Location loc) {
		locationObserved = loc;
		updateCameraBorders();
	}
	
	 public void render(Graphics g) {
		 for(int col = topLeftTileVisible.getColumn(); col < botRightTileVisible.getColumn(); col++){
	 		for(int row = topLeftTileVisible.getRow(); row < botRightTileVisible.getRow() ;row++){
	 			locationObserved.getGrid().getTiles()[(row*locationObserved.getGrid().getGridColumns())+col].render(g); 			
	 		}
		 }
	 }
	
	
	private void updateCameraBorders() {
		topLeftTileVisible = locationObserved.getGrid().findTileOnMouseCoords( x, y, scale);
		botRightTileVisible = locationObserved.getGrid().findTileOnMouseCoords( x + width, y + height, scale);

	}
	
	//Getters&Setters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}


	public double getScale() {
		return scale;
	}

}

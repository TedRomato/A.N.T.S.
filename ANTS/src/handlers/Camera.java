package handlers;



import java.awt.Graphics;

import world.Location;
import world.Tile;

public class Camera {
	int x, y; 
	int width, height;
	double scale = 1; 
	Location locationObserved;
	Tile topLeftTileVisible;
	Tile botRightTileVisible;
	
	 public void render(Graphics g) {
		 for(int col = topLeftTileVisible.getColumn(); col < botRightTileVisible.getColumn(); col++){
	 		for(int row = topLeftTileVisible.getRow(); row < botRightTileVisible.getRow() ;row++){
	 			locationObserved.getGrid().getTiles()[(row*locationObserved.getGrid().getGridColumns())+col].render(g); 			
	 		}
		 }
	 }
	
	
	private void updateCameraBorders() {
		topLeftTileVisible = locationObserved.getGrid().findTileOnCoords( x, y);
		botRightTileVisible = locationObserved.getGrid().findTileOnCoords( x + width, y + height);

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

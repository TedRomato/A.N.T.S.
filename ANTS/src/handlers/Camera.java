package handlers;

import com.sun.jdi.Location;

public class Camera {
	int x, y; 
	double scale = 1;
	Location locationObserved;
	int columnsToRenderBoundaries;
	int rowsToRenderBoundaries;
	
	

	
	
	/*TODO:
	 * render
	 * 
	 * 
	 * 
	 */
	
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

}

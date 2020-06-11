package handlers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import handlers.Input.WheelMove;
import interfacePackage.Game;
import world.Location;
import world.Tile;


public class Camera {
	int x = 500, y = 500; 
	int width, height;
	double scale = 0; 
	int[] zoomRange = new int[] {-1,1};
	double scaleChunk;
	int chunkAmount;
	int currentChunk;
	int tileRenderSize = Tile.tileSideLenght;

	Location locationObserved;
	
	BufferedImage bg;
	
	CameraMover cameraMover;
 
	//TODO : zoomovaani doprostred
	int leftMostRenderColumn;
	int rightMostRenderColumn;
	int topMostRenderRow;
	int botMostRenderRow;
	
	int maxDistanceFromBorderX;
	int maxDistanceFromBorderY;

	
	public Camera(Location loc, int width, int height) {
		
		this.width = width;
		this.height = height;
		maxDistanceFromBorderY = height/2;
		maxDistanceFromBorderX = width/2;
		cameraMover = new CameraMover(width, height);
		locationObserved = loc;
		bg = locationObserved.bg;
		scaleChunk = 2/ (double)(Tile.tilePossibleSizeRange[1] - Tile.tilePossibleSizeRange[0]);
		chunkAmount = (int) Math.round(2/scaleChunk);
		currentChunk = 0;
		updateCameraBorders();
		setTileRender();

	}
	
	public void renderMap(Graphics2D g) {
		 g.drawImage(bg, 0 - x, 0 - y, (int) (locationObserved.getGrid().getGridColumns()*tileRenderSize), (int)(locationObserved.getGrid().getGridRows()*tileRenderSize), null);

		 //render grid
		/* for(int col = leftMostRenderColumn; col < rightMostRenderColumn; col++){
			 for(int row = topMostRenderRow; row < botMostRenderRow;row++){
				 locationObserved.getGrid().getTiles()[(row*locationObserved.getGrid().getGridColumns())+col].render(g); 	
			 }
		 }*/
	}
	
	public void renderGridSnappingObjects(Graphics2D g) {
		int objectsHandled = 0;
		for(int i = 0; i < locationObserved.gridSnappingObjects.getArr().length; i++) {
			if(locationObserved.gridSnappingObjects.getArr()[i] != -1) {
				
				locationObserved.objectsInLocation[locationObserved.gridSnappingObjects.getArr()[i]].render(g, this);
				
				if(objectsHandled >= locationObserved.gridSnappingObjects.getContents()) {
					return;
				}
			}
		}
	}
	
	
	public void move(int x, int y) {
		if(moveInRange(this.y+y,locationObserved.getGrid().getGridRows()*tileRenderSize, maxDistanceFromBorderY)) {
			this.y += y;
		}
		if(moveInRange(this.x+x,locationObserved.getGrid().getGridColumns()*tileRenderSize, maxDistanceFromBorderX)) {
			this.x += x;
		}
		updateCameraBorders();
		setTileRender();

	}
		
	
	public void updateCameraBorders() {

		leftMostRenderColumn = (int)(x/((int)(tileRenderSize)));
		rightMostRenderColumn = (int)((x + width)/((int)(tileRenderSize)) + 1);
		topMostRenderRow = (int)(y/(int)(tileRenderSize));
		botMostRenderRow = (int)((y + height)/(int)(tileRenderSize) + 1);
		checkAndFixCameraBorders();
		
	}
	//used for testing or initial set
	public void zoom(double d) {
		if(scale + d > zoomRange[0] && scale + d < zoomRange[1]) {
			scale += d;
			updateTileScale();		
		}
	}
	
	public void smoothZoom(double d){
		zoom(smoothOutZoom(d));
		
		
	}
	
	public void handleCameraMoving(int x, int y) {
		cameraMover.handleCameraMovement(x, y, this);
	}
	
	private void checkAndFixCameraBorders() {
		if(leftMostRenderColumn < 0) {
			leftMostRenderColumn = 0;
		}
		if(rightMostRenderColumn > locationObserved.getGrid().getGridColumns()) {
			rightMostRenderColumn = locationObserved.getGrid().getGridColumns();
		}
		if(topMostRenderRow < 0) {
			topMostRenderRow = 0;
		}
		if(botMostRenderRow > locationObserved.getGrid().getGridRows()) {
			botMostRenderRow = locationObserved.getGrid().getGridRows();
		}
		
	}
	
	private void setTileRender() {	
		for(int col = leftMostRenderColumn; col < rightMostRenderColumn; col++){
			for(int row = topMostRenderRow; row < botMostRenderRow;row++){
	 			locationObserved.getGrid().getTiles()[(row*locationObserved.getGrid().getGridColumns())+col].updateRender(this);; 			
			}
		}	
	}
	
	
	private boolean moveInRange(int newCoord, int size, int maxDistanceFromBorder) {
		if(newCoord > size - maxDistanceFromBorder) {
			return false;
		}else if(newCoord < 0 - maxDistanceFromBorder){
			return false;
		}
		return true;
	}
	
	
	private boolean updateTileScale() {
		for(int i = - chunkAmount/2; i < chunkAmount; i++) {
			if(i != currentChunk) {
				if(scale > (i)*scaleChunk && scale < (i+1)*scaleChunk) {
					int former = tileRenderSize;
					tileRenderSize = Tile.tileSideLenght + i;
					currentChunk = i;
					adjustAfterScale(former);
					updateCameraBorders();
					move(getZoomDifference(former,tileRenderSize, width)/2,getZoomDifference(former,tileRenderSize, height)/2);
					setTileRender();
					return true;
				}
			}	
		}
		return false;
	}
	
	private void adjustAfterScale(int formerTileRenderSize) {
		double i = (double)x / (double)formerTileRenderSize;
		x = (int) Math.round(i * (double)tileRenderSize);
		i = (double)y / (double)formerTileRenderSize;
		y = (int) Math.round(i * (double)tileRenderSize);;
		
	}
	
	
	private double smoothOutZoom(double zoom) {
		if(scale < -0.33) {
			return zoom/2.4;
		}else if(scale < 0.33) {
			return zoom/1.5;
		}else {
			return zoom;
		}
	}
	
	
	private int getZoomDifference(int formerSize, int currentSize, int range) {

		double lastScaleWidth = range/(double)(formerSize);
		double newScaleWidth = range/(double)(currentSize);

		return (int)Math.round((lastScaleWidth-newScaleWidth)*currentSize);
	}
	
	public static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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

	
	
	public void setScale(double scale) {
		this.scale = scale;	
	}

	public int getTileRenderSize() {
		return tileRenderSize;
	}
}

package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;

import handlers.Camera;
import world.Tile;

public class CollisionSquare {
	double x,y;
	Point rp;
	double side;
	
	public CollisionSquare(double rpx, double rpy, double side, Point mainPoint) {
		if(mainPoint.getX() == rpx && mainPoint.getY() == rpy) {
			rp = new Point(rpx, rpy); 
		}else {
			rp = new DependentPoint(rpx, rpy,mainPoint); 
		}
		this.side = side;
		setXYToRP();
	}
	
	public void setXYToRP() {
		x = rp.getX()-(Tile.tileSideLenght*side/2);
		y = rp.getY()-(Tile.tileSideLenght*side/2);
	}
	
	public void rotateAround(Point mainPoint, double angleToRotate) {
		rp.rotateAroundPoint(mainPoint, angleToRotate);
		setXYToRP();
	}
	
	public void move(double velX, double velY) {
		rp.move(velX, velY);
		setXYToRP();
	}
	
	
	public Point getRp() {
		return rp;
	}
	
	public void render(Graphics g, Camera camera) {
		g.drawRect((int)(x*camera.getTileRenderSize()/Tile.tileSideLenght - camera.getX()), (int)(y*camera.getTileRenderSize()/Tile.tileSideLenght - camera.getY()),(int)(camera.getTileRenderSize()*side) - 1, (int)(camera.getTileRenderSize()*side) - 1);
	}
	

	
	
}

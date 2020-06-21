package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;

import handlers.Camera;
import world.Tile;

public class CollisionSquare {
	double x,y;
	Point rp;
	double width, height;
	
	public CollisionSquare(double rpx, double rpy, double side, Point mainPoint) {
		if(mainPoint.getX() == rpx && mainPoint.getY() == rpy) {
			rp = new Point(rpx, rpy); 
		}else {
			rp = new DependentPoint(rpx, rpy,mainPoint); 
		}
		this.width = side;
		this.height = side;

		setXYToRP();
	}
	
	public void setXYToRP() {
		x = rp.getX()-(Tile.tileSideLenght*width/2);
		y = rp.getY()-(Tile.tileSideLenght*height/2);
	}
	
	public void rotateAround(Point mainPoint, double angleToRotate) {
		rp.rotateAroundPoint(mainPoint, angleToRotate);
		setXYToRP();
	}
	
	public void move(double velX, double velY) {
		rp.move(velX, velY);
		setXYToRP();
	}
	
	public boolean checkCollision(CollisionSquare other) {
		if(		this.getX() + this.getWidth()*Tile.tileSideLenght < other.getX() ||
				this.getX() > other.getX() + other.getWidth()*Tile.tileSideLenght) {
			return false;
		}
		if(		this.getY() + this.getHeight()*Tile.tileSideLenght < other.getY() ||
				this.getY() > other.getY() + other.getHeight()*Tile.tileSideLenght) {
			return false;
		}
		return true;	
	}
	
	public boolean checkCollision(Point p) {
		
		if(p.getX() > this.getX() && p.getX() < this.getX() + this.getWidth()*Tile.tileSideLenght) {

			if(p.getY() > this.getY() && p.getY() < this.getY() + this.getHeight()*Tile.tileSideLenght) {
				return true;
			}
		}
		System.out.println("false");

		return false;
	}
	
	
	
	public Point getRp() {
		return rp;
	}
	
	public void render(Graphics g, Camera camera) {
		g.drawRect((int)(x*camera.getTileRenderSize()/Tile.tileSideLenght - camera.getX()), (int)(y*camera.getTileRenderSize()/Tile.tileSideLenght - camera.getY()),(int)(camera.getTileRenderSize()*width) - 1, (int)(camera.getTileRenderSize()*height) - 1);
	}
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setRp(Point rp) {
		this.rp = rp;
	}


	

	
	
}

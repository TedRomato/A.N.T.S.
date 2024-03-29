package gameObjectClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import handlers.Camera;
import interfacePackage.Game;
import world.Tile;

public abstract class LivingObject extends GameObject{
	CollisionSquare[] colliders = new CollisionSquare[0];
	Point rp;
	Point goalDestination;
	DependentPoint md;
	double vel, velX, velY, xyRatio;
	boolean moving = true, facingUp, facingLeft;
	double angle;
	double testingAngleToRotate = 0.02;
	boolean distanceDone = false;
	public LivingObject(Point rp, double vel) {
		this.rp = rp;
		this.md = new DependentPoint(rp.getX(), rp.getY() - 50, rp);
		this.vel = vel;
		angle = md.getAngleFrom(rp);
		updateAfterRotate();
	}
	
	
	public void updateLivingObject() {
		//moves ant
		move();
		
		//ant decides if it still needs to be moving to reach goal destination
		decideIfContinueMoving();
		
		//If right mouse pressed ant rotates to goal location
		if(Game.input.isRightMousePressed()) {
			setNewGoalDestination(new Point(Game.input.getCursorXOnMap(), Game.input.getCursorYOnMap()));
		}
	}
	
	protected void rotateToPoint(Point toRotateTo) {
		rotate((toRotateTo.getAngleFrom(rp) - md.getAngleFrom(rp)));
	}
	
	protected void rotate(double angleToRotate) {
		angle += angleToRotate;
		md.rotateAroundPoint(rp, angleToRotate);
		for(CollisionSquare cs : colliders) {
			cs.rotateAround(rp, angleToRotate);
		}
		updateAfterRotate();
	}
	protected void move() {
		if(moving) {
			rp.move(velX, velY);
			md.move(velX, velY);
			for(CollisionSquare cs : colliders) {
				cs.move(velX, velY);
			}
		}
	}
	
	protected void makeMainCollider(double side) {
		colliders = new CollisionSquare[1];
		colliders[0] = new CollisionSquare(rp.getX(), rp.getY(), side, rp);
	}
	
	protected void updateAfterRotate(){
		voidGetNewRatio();
		setVelDirection();
		setVelocitySizes();
		setVelocityDirections();
	}
	
	protected void voidGetNewRatio() {
		xyRatio = Math.abs((rp.getX() - md.getX())/(rp.getY() - md.getY()));
	}
	
	protected void setVelocitySizes() {
		if(xyRatio == Double.POSITIVE_INFINITY) {
			velY = 0;
			velX = vel;
		}else {
			velY = Math.sqrt(Math.pow(vel, 2)/(Math.pow(xyRatio, 2)+1));
			velX = xyRatio*velY;
		}
		
	}
	
	protected void setVelocityDirections() {
		if(facingUp) {
			velY = -Math.abs(velY);
		}else {
			velY = Math.abs(velY);
		}
		if(facingLeft) {
			velX = -Math.abs(velX);
		}else {
			velX = Math.abs(velX);
		}
	}
	
	protected void setVelDirection() {;
		if(md.getY() < rp.getY()) {
			facingUp = true;
		}else {
			facingUp = false;
		}
		if(md.getX() < rp.getX()) {
			facingLeft = true;
		}else {
			facingLeft = false;
		}
	}
	
	protected void addCollisionSquare(double offsetX, double offsetY, double side) {
		CollisionSquare[] temp = colliders;
		colliders = new CollisionSquare[temp.length+1];
		for(int i = 0; i < temp.length; i++) {
			colliders[i] = temp[i];
		}
		colliders[colliders.length - 1] = new CollisionSquare(rp.getX() - offsetX, rp.getY() - offsetY, side, rp);
		
	}
	
	protected void decideIfContinueMoving() {
		if(goalDestination.getDistanceFromPoint(this.rp) < 2) {
			moving = false;
		}
	}
	
	protected void setNewGoalDestination(Point p) {
		goalDestination = p;
		moving = true;
		rotateToPoint(goalDestination);
		
	}
	
	public void render(Graphics2D g, Camera c) {
		double tileRatio = (double)c.getTileRenderSize()/(double)Tile.tileSideLenght;

		rotateImage(g, c, tileRatio);
		// Colliders render
		g.setColor(Color.RED);
	/*	for(CollisionSquare cs : colliders) {
			cs.render(g, c);
		}*/
		
		//rp
//		g.setColor(Color.blue);
//		g.fillRect((int)(rp.getX()*tileRatio - c.getX()),(int)(rp.getY()*tileRatio - c.getY()),20,20);
		//md
//		g.setColor(Color.red);
//		g.fillRect((int)(md.getX()*tileRatio - c.getX()),(int)(md.getY()*tileRatio - c.getY()),20,20);
		//goal
//		g.setColor(Color.green);
//		g.fillRect((int)(goalDestination.getX()*tileRatio - c.getX()),(int)(goalDestination.getY()*tileRatio - c.getY()),20,20);
		
	}
	
	
	private void rotateImage(Graphics2D g,Camera camera, double tileRatio) {
		AffineTransform trans = new AffineTransform();
		trans.rotate(angle,(int)(rp.getX()*tileRatio - camera.getX()),(int)(rp.getY()*tileRatio - camera.getY()));
		AffineTransform old = g.getTransform();
		g.transform(trans);
		g.drawImage(sprites[currentSpritePointer], 
				(int)(rp.getX()*tileRatio- camera.getX()- imageOffsetX*tileRatio),
				(int)(rp.getY()*tileRatio-camera.getY()-imageOffsetY*tileRatio),
				(int)(imageWidth*tileRatio),
				(int)(imageHeight*tileRatio),null);
		g.setTransform(old);
	}
	
	
	
	
	public boolean checkIfInViewport(Camera c) {
		return false;
	}
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
}

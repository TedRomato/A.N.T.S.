package gameObjectClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import handlers.Camera;
import world.Tile;

public abstract class LivingObject extends GameObject{
	CollisionSquare[] colliders;
	Point rp;
	DependentPoint md;
	double vel, velX, velY, xyRatio;
	boolean moving, facingUp, facingLeft;
	double angle = 0;
	double testingAngleToRotate = 0.02;
	boolean distanceDone = false;
	public LivingObject(Point rp, double mdXOffset, double mdYOffset, double vel) {
		this.rp = rp;
		this.md = new DependentPoint(rp.getX() - mdXOffset, rp.getY() - mdYOffset, rp);
		this.vel = vel;
		updateAfterRotate();
	}
	
	//TODO rotate rozbiji rotujici collision ctverce po x 
	
	public void updateLivingObject() {
		rotate(testingAngleToRotate);
		move(velX,velY);
	}
	
	protected void rotateToPoint(Point toRotateTo) {
		rotate(toRotateTo.getAngleFrom(rp) - md.getAngleFrom(rp));
	}
	
	protected void rotate(double angleToRotate) {
		angle += angleToRotate;
		md.rotateAroundPoint(rp, angleToRotate);
		for(CollisionSquare cs : colliders) {
			cs.rotateAround(rp, angleToRotate);
		}
		updateAfterRotate();
	}
	protected void move(double velX, double velY) {
		rp.move(velX, velY);
		md.move(velX, velY);
		for(CollisionSquare cs : colliders) {
			cs.move(velX, velY);
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
		velY = Math.sqrt(Math.pow(vel, 2)/(Math.pow(xyRatio, 2)+1));
		velX = xyRatio*velY;
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
	
	public void render(Graphics g, Camera c) {
		g.setColor(Color.RED);
		for(CollisionSquare cs : colliders) {
			cs.render(g, c);
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
	
	//TODO
	public void rotateImage(Graphics2D g,BufferedImage img,double ra, Corner rp, int width, int height, int rpX, int rpY) {
		AffineTransform trans = new AffineTransform();
		trans.rotate(Math.toRadians(ra),(rp.getX()*Game.camera.toMultiply() + Game.camera.toAddX()),(int)(rp.getY()*Game.camera.toMultiply() + Game.camera.toAddY()));
		AffineTransform old = g.getTransform();
		g.transform(trans);
		g.drawImage(resize(img,(int)(width*Game.screenRatio),(int)(height*Game.screenRatio)),(int)((rp.getX()-rpX)*Game.camera.toMultiply() + Game.camera.toAddX()),(int)((rp.getY()-rpY)*Game.camera.toMultiply() + Game.camera.toAddY()),null);
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

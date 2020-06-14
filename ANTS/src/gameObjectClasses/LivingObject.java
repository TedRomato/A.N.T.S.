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
	
	public void render(Graphics2D g, Camera c) {
		double tileRatio = (double)c.getTileRenderSize()/(double)Tile.tileSideLenght;
	/*	g.drawImage(sprites[currentSpritePointer], 
				(int)(rp.getX()*tileRatio - c.getX() - imageOffsetX*tileRatio), 
				(int)(rp.getY()*tileRatio - c.getY()  - imageOffsetY*tileRatio),
				(int)Math.round((double)(imageWidth*tileRatio)), 
				(int)Math.round((double)(imageHeight*tileRatio)), null);*/
		rotateImage(g, c, tileRatio);
		g.setColor(Color.RED);
		for(CollisionSquare cs : colliders) {
			cs.render(g, c);
		}
		
	}
	
	public static void rotateImage(Graphics2D g,BufferedImage img,int width, int height,double ra/*angle of rotation*/, Point rp, int rpX/*rotation point offset X*/, int rpY/*rotation point offset Y*/) {
		AffineTransform trans = new AffineTransform();
		trans.rotate(Math.toRadians(ra),(int)(rp.getX()/* +*camera scale*/),(int)(rp.getY()/* +*camera scale)*/));
		AffineTransform old = g.getTransform();
		g.transform(trans);
		g.drawImage(img, (int)(rp.getX()-rpX/* +*camera scale)*/),(int)(rp.getY()-rpY/* +*camera scale)*/),width,height,null);
		g.setTransform(old);
	}
	
	public void rotateImage(Graphics2D g,Camera camera, double tileRatio) {
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
	
	protected void addCollisionSquare(double offsetX, double offsetY, double side) {
		CollisionSquare[] temp = colliders;
		colliders = new CollisionSquare[temp.length+1];
		for(int i = 0; i < temp.length; i++) {
			colliders[i] = temp[i];
		}
		colliders[colliders.length - 1] = new CollisionSquare(rp.getX() - offsetX, rp.getY() - offsetY, side, rp);
		
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

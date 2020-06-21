package gameObjectClasses;

import java.awt.Color;
import java.awt.Graphics2D;

import handlers.Camera;
import world.Tile;

//TODO: off grid objects from living objects - > make items

public abstract class OffGridObject extends GameObject{
	CollisionSquare[] colliders = new CollisionSquare[0];
	Point rp;
	
	
	public OffGridObject(Point point) {
		rp = point;
	}

	@Override
	public void render(Graphics2D g, Camera camera) {
		double tileRatio = (double)camera.getTileRenderSize()/(double)Tile.tileSideLenght;
		g.drawImage(sprites[currentSpritePointer], 
				(int)(rp.getX()*tileRatio- camera.getX()- imageOffsetX*tileRatio),
				(int)(rp.getY()*tileRatio-camera.getY()-imageOffsetY*tileRatio),
				(int)(imageWidth*tileRatio),
				(int)(imageHeight*tileRatio),null);
		// Colliders render
			g.setColor(Color.RED);
			for(CollisionSquare cs : colliders) {
				cs.render(g, camera);
			}
		
	}

	@Override
	public boolean checkIfInViewport(Camera c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean checkCollision(OffGridObject ob) {
		for(CollisionSquare ot : ob.getColliders()) {
			for(CollisionSquare th : this.getColliders()) {
				if(ot.checkCollision(th)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkCollision(Point p) {
	
		for(CollisionSquare th : this.getColliders()) {
			if(th.checkCollision(p)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	protected void makeMainCollider(double side) {
		colliders = new CollisionSquare[1];
		colliders[0] = new CollisionSquare(rp.getX(), rp.getY(), side, rp);
	}
	
	protected void addCollisionSquare(double offsetX, double offsetY, double side) {
		CollisionSquare[] temp = colliders;
		colliders = new CollisionSquare[temp.length+1];
		for(int i = 0; i < temp.length; i++) {
			colliders[i] = temp[i];
		}
		colliders[colliders.length - 1] = new CollisionSquare(rp.getX() - offsetX, rp.getY() - offsetY, side, rp);
		
	}
	
	public void move(double velX, double velY) {
		rp.move(velX, velY);
		for(CollisionSquare cs : colliders) {
			cs.move(velX, velY);
		}
	}
	
	public Point getRp() {
		return rp;
	}


	public void setRp(Point rp) {
		this.rp = rp;
	}
	
	public CollisionSquare[] getColliders() {
		return colliders;
	}

}

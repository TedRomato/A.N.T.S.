package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;

import handlers.Camera;
import world.Tile;

public abstract class Item extends OffGridObject{

	public Item(Point point) {
		super(point);
		// TODO Auto-generated constructor stub
	}

	enum State{
		Carried,
		Laying,
		BeingUsed
	}
	
	State currentState = State.Laying;
	
	public void pickUp() {
		currentState = State.Carried;

	}
	
	
	public void drop(Point whereToDrop) {
		this.setRp(new Point(whereToDrop.getX(), whereToDrop.getY()));
		for(CollisionSquare cs : colliders) {
			cs.setRp(new Point(whereToDrop.getX(), whereToDrop.getY()));
			cs.setXYToRP();
		}
		currentState = State.Laying;
	}
	
	

	
	@Override
	public void render(Graphics2D g, Camera c) {
		switch(currentState) {
			case Laying:
				super.render(g, c);
				break;
				
			case Carried:
				//renderNothing
				break;
				
			case BeingUsed:
				//renderNothing
				break;
		}
	}
	
	public boolean isPickedUp() {	
		if(currentState == State.Carried) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}

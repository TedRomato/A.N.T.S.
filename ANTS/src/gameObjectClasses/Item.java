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
	
	
	
}

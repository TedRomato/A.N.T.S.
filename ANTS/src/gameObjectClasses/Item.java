package gameObjectClasses;

import java.awt.Graphics;

import handlers.Camera;

public class Item extends GridSnappingObject{
	
	enum State{
		Carried,
		Laying,
		BeingUsed
	}
	
	State currentState = State.Laying;
	
	
	
	@Override
	public void render(Graphics g, Camera c) {
		switch(currentState) {
			case Laying:
				//render image
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

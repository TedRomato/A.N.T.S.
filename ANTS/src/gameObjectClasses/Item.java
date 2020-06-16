package gameObjectClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;

import handlers.Camera;

public class Item extends GridSnappingObject{
	
	enum State{
		Carried,
		Laying,
		BeingUsed
	}
	
	State currentState = State.Laying;
	
	
	
	@Override
	public void render(Graphics2D g, Camera c) {
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

package gameObjectClasses;

import java.awt.Graphics;

public class Item extends GridSnappingObject{
	
	enum State{
		Carried,
		Laying,
		BeingUsed
	}
	
	State currentState = State.Laying;
	
	@Override
	void render(Graphics g) {
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

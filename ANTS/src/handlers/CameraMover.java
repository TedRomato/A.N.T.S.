package handlers;

public class CameraMover {
	MouseLocationTrigger top;
	MouseLocationTrigger bot;
	MouseLocationTrigger left;
	MouseLocationTrigger right;
	
	public CameraMover(int screenWidth, int screenHeight) {
		int stripWidth = screenWidth/16;
		top   = new MouseLocationTrigger(0,0,screenWidth,stripWidth);
		bot   = new MouseLocationTrigger(0,screenHeight - stripWidth,screenWidth,stripWidth);
		left  = new MouseLocationTrigger(0, 0, stripWidth, screenHeight);
		right = new MouseLocationTrigger(screenWidth - stripWidth, 0, stripWidth, screenHeight);
	}
	
	public void handleCameraMovement(int x, int y, Camera c) {
		int speed = 7;
		int moveX = 0; int moveY = 0;
		if(top.checkIfMouseIn(x,y)){ 
			moveY = -speed;
		}
		if(bot.checkIfMouseIn(x,y)){ 
			moveY = speed;
		}
		if(left.checkIfMouseIn(x,y)){ 
			moveX = -speed;
		}
		if(right.checkIfMouseIn(x,y)){ 
			moveX = speed;
		}
		c.move(moveX, moveY);
	}
}

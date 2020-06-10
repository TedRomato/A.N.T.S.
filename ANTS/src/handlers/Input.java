package handlers;

public class Input {
	char[] pressedChars = new char[5];
	char blank;
	boolean leftMousePressed = false;
	boolean rightMousePressed = false;
	int CursorX, CursorY;
	enum WheelMove{DOWN,UP}
	WheelMove move;
	
	public boolean checkIfKeyPressed(char key) {
		for(char chars : pressedChars) {
			if(key == chars) {
				return true;
			}
		}
		return false;
	}
	public void keyPressed(char key) {
		boolean IsPressed = false;
		boolean added = false;
		for(char chars : pressedChars) {
			if(chars == key) {
				IsPressed = true;
			}
		}
		if(!IsPressed) {	
			
			for(int i = 0; i < pressedChars.length;i++) {
				if(pressedChars[i] == blank && !added) {
					pressedChars[i] = key;
					added = true;
				}
				
				
			}
		}
	}
	public void keyReleased(char key) {
		boolean removed = false;
		for(int i = 0; i < pressedChars.length;i++) {
			if(pressedChars[i] == key && !removed) {
				pressedChars[i] = blank;
				removed = true;
			}
		}
	}
	public void checkMouseWheelMove(int notches){
		if(notches > 0) {
			move = WheelMove.UP;
		}
		if(notches < 0) {
			move = WheelMove.DOWN;
		}
	}
	
	public int getCursorX() {
		return CursorX;
	}

	public void setCursorX(int cursorX) {
		CursorX = cursorX;
	}

	public int getCursorY() {
		return CursorY;
	}

	public void setCursorY(int cursorY) {
		CursorY = cursorY;
	}

	public WheelMove getMove() {
		return move;
	}
	public void setMove(WheelMove move) {
		this.move = move;
	}
	public boolean isLeftMousePressed() {
		return leftMousePressed;
	}
	public void setLeftMousePressed(boolean leftMousePressed) {
		this.leftMousePressed = leftMousePressed;
	}
	public boolean isRightMousePressed() {
		return rightMousePressed;
	}
	public void setRightMousePressed(boolean rightMousePressed) {
		this.rightMousePressed = rightMousePressed;
	};
	
}

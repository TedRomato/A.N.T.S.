package handlers;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObjectClasses.CollisionSquare;
import gameObjectClasses.Point;
import world.Tile;

public class Input {
	char[] pressedChars = new char[5];
	char blank;
	boolean leftMousePressed = false;
	boolean rightMousePressed = false;
	int CursorX, CursorY;
	int CursorXOnMap, CursorYOnMap;
	boolean selectionRectActive = false;
	boolean selectNow = false;
	CollisionSquare selectionRect = new CollisionSquare(0,0, 0,new Point(0,0));
	int selectionRectX, selectionRectY, selectionRectX2, selectionRectY2;
//	int topLeftSelectX,  topLeftSelectY,  selectHeight,  selectWidth;
	public enum WheelMove{DOWN,NONE,UP};
	WheelMove move;
	
	public enum SelectionType{SELECT, GRAB, ATTACK, DROP}
	SelectionType selectionType = SelectionType.SELECT;
	
	//
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
	public double WheelMoveForAmount(double d) {
		if(move == WheelMove.UP) {
			return -d;
		}
		if(move == WheelMove.DOWN) {
			return d;
		}
		return 0;
	}
	public void checkMouseWheelMove(int notches){
		if(notches > 0 ) {
			move = WheelMove.UP;
		}
		if(notches < 0 ) {
			move = WheelMove.DOWN;
		}
		if(notches == 0) {
			move = WheelMove.NONE;
		}
	}
	
	public void updateInput(Camera c) {
		updateSelectionType();
		handleSelectionRect(c);
	}
	
	public void updateSelectionType() {
		if(checkIfKeyPressed('c')) {
			selectionType = SelectionType.GRAB;
		}
		else if(checkIfKeyPressed('d')) {
			selectionType = SelectionType.DROP;
		}
		else if(checkIfKeyPressed('a')) {
			selectionType = SelectionType.ATTACK;
		}
		if(isRightMousePressed()) {
			selectionType = SelectionType.SELECT;
		}
	}
	
	public void handleSelectionRect(Camera c) {

		
		if(!selectionRectActive && isLeftMousePressed()) {
			selectionRectActive = true;
			selectionRectX = CursorXOnMap;
			selectionRectY = CursorYOnMap;
		}
		else if(selectionRectActive && isLeftMousePressed()) {
			selectionRectX2 = CursorXOnMap;
			selectionRectY2 = CursorYOnMap;
		}
		else if(selectionRectActive && !isLeftMousePressed()) {
			selectionRectActive = false;
			selectNow = true;
			selectionRectX2 = CursorXOnMap;
			selectionRectY2 = CursorYOnMap;
		}
		
		updateSelectionRectRender(c);
	}
	
	private void updateSelectionRectRender(Camera c) {
		
		if(selectionRectX > selectionRectX2) {
			selectionRect.setX(selectionRectX2);
			selectionRect.setWidth((double)(selectionRectX - selectionRectX2)/Tile.tileSideLenght);
		}else {
			selectionRect.setX(selectionRectX);
			selectionRect.setWidth((double)(selectionRectX2 - selectionRectX)/Tile.tileSideLenght);
		}
		
		if(selectionRectY > selectionRectY2) {
			selectionRect.setY(selectionRectY2);
			selectionRect.setHeight((double)(selectionRectY - selectionRectY2)/Tile.tileSideLenght);
		}else {
			selectionRect.setY(selectionRectY);
			selectionRect.setHeight((double)(selectionRectY2 - selectionRectY)/Tile.tileSideLenght);
		}
		
		
	}
	
	public void restetSelectionRect() {
		selectionRect.setX(0);
		selectionRect.setY(0);
		selectionRect.setWidth(0);
		selectionRect.setHeight(0);;
	}
	
	
	public boolean checkIfInsideSelectionRect(CollisionSquare[] colliders) {
		for(CollisionSquare cs : colliders) {
			if(cs.checkCollision(this.selectionRect)) {
				return true;
			}
		}
		
		return false;
	}

	public void renderSelectionRect(Graphics2D g, Camera c) {
	
		if(selectionRectActive) {
			g.setColor(Color.green);
			selectionRect.render(g, c);
		}
		
	}
	
	public int getCursorX() {
		return CursorX;
	}

	public void setCursorX(int cursorX, Camera c) {
		CursorX = cursorX;
		CursorXOnMap = (int) (c.getX()/(double)c.getTileRenderSize()*(double)Tile.tileSideLenght + (double)cursorX/(double)c.getTileRenderSize()*(double)Tile.tileSideLenght);
	}
	
	public int getCursorXOnMap() {
		return CursorXOnMap;
	}


	public int getCursorY() {
		return CursorY;
	}

	public void setCursorY(int cursorY, Camera c) {
		CursorY = cursorY;
		CursorYOnMap = (int) (c.getY()/(double)c.getTileRenderSize()*(double)Tile.tileSideLenght + (double)cursorY/(double)c.getTileRenderSize()*(double)Tile.tileSideLenght);
		
		
	}
	
	public int getCursorYOnMap() {
		return CursorYOnMap;
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
	}
	
	public boolean isSelectNow() {
		return selectNow;
	}
	public void setSelectNow(boolean selectNow) {
		this.selectNow = selectNow;
	}
	
	public SelectionType getSelectionType() {
		return selectionType;
	}
	public void setSelectionType(SelectionType st) {
		selectionType = st;
	}

	
	
	
}

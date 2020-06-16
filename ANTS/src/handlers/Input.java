package handlers;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObjectClasses.Point;
import interfacePackage.Game;
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
	int selectionRectX, selectionRectY, selectionRectX2, selectionRectY2;
	int topLeftSelectX,  topLeftSelectY,  selectHeight,  selectWidth;
	public enum WheelMove{DOWN,NONE,UP};
	WheelMove move;
	
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
	
	public void handleSelectionRect() {

		
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
		
		updateSelectionRectRender();
	}
	
	private void updateSelectionRectRender() {
		if(selectNow) {
			topLeftSelectX = 0;
			topLeftSelectY = 0;
			selectHeight = 0;
			selectWidth = 0;
		}else {
			if(selectionRectX > selectionRectX2) {
				topLeftSelectX = selectionRectX2;
				selectWidth = selectionRectX - selectionRectX2;
			}else {
				topLeftSelectX = selectionRectX;
				selectWidth = selectionRectX2 - selectionRectX;
			}
			
			if(selectionRectY > selectionRectY2) {
				topLeftSelectY = selectionRectY2;
				selectHeight = selectionRectY - selectionRectY2;
			}else {
				topLeftSelectY = selectionRectY;
				selectHeight = selectionRectY2 - selectionRectY;
			}
		}
		
	}
	
	public boolean checkIfInsideSelectionRect(Point p) {
		if(p.getX() > selectionRectX && p.getX() < selectionRectX2) {
			if(p.getY() > selectionRectY && p.getY() < selectionRectY2) {
				return true;
			}
			if(p.getY() < selectionRectY && p.getY() > selectionRectY2) {
				return true;
			}
		}
		else if(p.getX() < selectionRectX && p.getX() > selectionRectX2) {
			if(p.getY() > selectionRectY && p.getY() < selectionRectY2) {
				return true;
			}
			if(p.getY() < selectionRectY && p.getY() > selectionRectY2) {
				return true;
			}
		}
		return false;
	}
	
	public void renderSelectionRect(Graphics2D g, Camera c) {
	
		if(selectionRectActive) {
			double tileRatio = (double)c.getTileRenderSize()/(double)Tile.tileSideLenght;
			g.setColor(Color.green);
			g.drawRect((int)(topLeftSelectX*tileRatio - c.getX()),(int)(topLeftSelectY*tileRatio - c.getY())
					,(int)(selectWidth*tileRatio),(int)(selectHeight*tileRatio));
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

	
	
	
}

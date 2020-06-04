package handlers;

import java.awt.Graphics;

import gameObjectClasses.GameObject;

public class RenderHandler implements Handler{
	HandlerArray renderArr;
	Graphics g;

	public RenderHandler(int maxLen) {
		renderArr = new HandlerArray(maxLen);
	}
	
	@Override
	public void handle(GameObject[] locationArr) {
		int itemsHandled = 0;
		for(int i = 0; i < renderArr.getArr().length; i++) {
			if(renderArr.getArr()[i] != -1) {
				itemsHandled++;
				if(locationArr[renderArr.getArr()[i]] == null) {
					renderArr.removeFromArray(i);;
				}else {
					locationArr[renderArr.getArr()[i]].render(g);
					itemsHandled++;
				}
				
				if(itemsHandled == renderArr.getContents()) {
					break;
				}
			}
		}
	}
	
	public void addToRenderArray(int index) {
		renderArr.addToHandlerArr(index);
	}


	
	
	
	
	
	
}

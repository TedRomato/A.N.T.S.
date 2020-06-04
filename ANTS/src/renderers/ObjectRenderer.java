package renderers;


import java.awt.Graphics2D;

import gameObjectClasses.GameObject;

public class ObjectRenderer extends Renderer{
	
	
	public ObjectRenderer(int maxLen) {
		super(maxLen);
	}

	public void handle(GameObject[] locationArr, Graphics2D g2) {
		int itemsHandled = 0;
		for(int i = 0; i < renderArr.getArr().length; i++) {
			if(renderArr.getArr()[i] != -1) {
				itemsHandled++;
				if(locationArr[renderArr.getArr()[i]] == null) {
					renderArr.removeFromArray(i);;
				}else {
					locationArr[renderArr.getArr()[i]].render(g2);
					itemsHandled++;
				}
				
				if(itemsHandled == renderArr.getContents()) {
					break;
				}
			}
		}
	}
		
}

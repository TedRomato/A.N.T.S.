package renderers;


import gameObjectClasses.GameObject;

public class ObjectRenderer extends Renderer{
	
	
	public ObjectRenderer(int maxLen) {
		super(maxLen);
	}

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
		
}

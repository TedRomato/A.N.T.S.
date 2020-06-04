package renderers;

import java.awt.Graphics2D;

import gameObjectClasses.GameObject;
import world.Grid;
import world.Location;

public class TileRenderer extends Renderer{

	public TileRenderer(int maxLen) {
		super(maxLen);
	}

	public void handle(Grid grid, Graphics2D g) {
		int itemsHandled = 0;
		for(int i = 0; i < renderArr.getArr().length; i++) {
			if(renderArr.getArr()[i] != -1) {
				itemsHandled++;
				if(grid.getTiles()[renderArr.getArr()[i]] == null) {
					renderArr.removeFromArray(i);;
				}else {
					grid.getTiles()[renderArr.getArr()[i]].render(g);
					itemsHandled++;
				}
				
				if(itemsHandled == renderArr.getContents()) {
					break;
				}
			}
		}
	}
	
}

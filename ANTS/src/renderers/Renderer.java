package renderers;

import java.awt.Graphics;

import handlers.ContentArray;

public class Renderer {
	ContentArray renderArr;
	Graphics g;

	public Renderer(int maxLen) {
		renderArr = new ContentArray(maxLen);
	}
	
	public void addToRenderArray(int index) {
		renderArr.addToHandlerArr(index);
	}
}

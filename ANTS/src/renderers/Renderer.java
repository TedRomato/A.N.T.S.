package renderers;



import handlers.ContentArray;

public class Renderer {
	ContentArray renderArr;

	public Renderer(int maxLen) {
		renderArr = new ContentArray(maxLen);
	}
	
	public void addToRenderArray(int index) {
		renderArr.addToHandlerArr(index);
	}
}

package handlers;

public class MouseLocationTrigger {
	int x, y, width, height;
	public MouseLocationTrigger(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean checkIfMouseIn(int x, int y) {
		if(x >= this.x && y >= this.y) {
			if(x <= this.x + width && y <= this.y + height) {
				return true;
			}
		}
		return false;
	}
	
}

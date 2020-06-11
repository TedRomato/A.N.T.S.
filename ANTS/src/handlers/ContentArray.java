package handlers;

//use: stores indexes of any contents. 
//why? This special array knows how many contents it contains
//-> fast iteration. You can stop Iterating as soon as you go 
//through amount of objects == ContentArray.contents
//you can stop iterating
//smart adding - > every time you add something you add 
//something it is placed closest free place from index 0


public class ContentArray {
	int[] arr;
	int contents = 0;
	
	public ContentArray(int maxLen) {
		initializeArray(maxLen);
	}
	
	public void addToHandlerArr(int gameObject) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == -1) {
				arr[i] = gameObject;
				contents ++;
				return;
			}
		}
	}
	
	public void removeFromArray(int index) {
		if(arr[index] != -1) {
			arr[index] = -1;
			contents --;
		}
		
	}
	
	private void initializeArray(int len) {
		arr = new int[len];
		for(int i = 0; i < len; i++) {
			arr[i] = -1;
		}
	}
	
	public int[] getArr() {
		return arr;
	}
	
	public int getContents() {
		return contents;
	}
}

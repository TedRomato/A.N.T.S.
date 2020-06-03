package world;

public class Grid {
	private int gridColumns, gridRows;
	private int mapSize;
	Tile[] worldGrid;
	
	public Grid(int gridColumns, int gridRows) {
		this.gridColumns = gridColumns;
		this.gridRows = gridRows;
		mapSize = gridColumns*gridRows;
		worldGrid = new Tile[mapSize];
		constructGrid();
	}

	protected void constructGrid() {
		for(int i = 0; i < mapSize; i++) {
			worldGrid[i] = new Tile((i/gridRows), i - (i/gridRows) );
		}
	}
	
	
	
	
	
	
	//Special getters&setters
	public Tile findTileOnCoords(int x, int y) {
		return worldGrid[y/gridRows*gridColumns + x/gridColumns];
	}
	
	//Getters&Setters
	public Tile[] getTiles() {
		return worldGrid;
	}
	
	public int getGridColumns() {
		return gridColumns;
	}

	public void setGridColumns(int gridColumns) {
		this.gridColumns = gridColumns;
	}

	public int getGridRows() {
		return gridRows;
	}

	public void setGridRows(int gridRows) {
		this.gridRows = gridRows;
	}

	public Tile[] getWorldGrid() {
		return worldGrid;
	}

}

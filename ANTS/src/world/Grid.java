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
	
	
	
	
	//Getters&Setters
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

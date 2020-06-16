package world;

import java.awt.Color;

import handlers.Camera;

//Container for Tile
//Can grant acces to Tile at mouse coords
//acces to size of the grid
//to width in tiles (columns) and height (rows) 


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
		for(int r = 0; r < gridColumns; r++) {
			for(int c = 0; c < gridRows; c++) {
				worldGrid[r*gridColumns + c] = new Tile(c, r);
			}
		}
	}
	
	public void printTileCoords() {
		for(Tile t : worldGrid) {
			t.printCoords();
		}
	}
	
	
	//Special getters&setters
	public Tile findTileOnMouseCoords(int x, int y, Camera camera) {
		int index = (int)(y/(int)(Tile.tileSideLenght*camera.getScale())*gridColumns) 
				+	(int)(x/(int)(Tile.tileSideLenght*camera.getScale()));
		System.out.println("index : " + index);
		if(index > 0 && index < worldGrid.length) {
			System.out.println("column : " + worldGrid[index].getColumn() + " row : " + worldGrid[index].getRow());
			return worldGrid[index];
			
		}
		System.out.println("null");

		return null;
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

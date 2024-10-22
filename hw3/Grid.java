package hw3;

import api.Tile;

/**
 * @author issmale bekri
 */

/**
 * Represents the game's grid.
 */
public class Grid {
	
	/**
	 * variable that holds width
	 */
	private int width;
	/**
	 * variable that holds height
	 */
	private int height;
	/**
	 * 2D array of tiles 
	 */
	private Tile[][] gridboard;
	
	
	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		gridboard = new Tile[height][width];
		
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public api.Tile getTile(int x, int y) {
		
		return gridboard[y][x];
	}

	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y) {
		
		tile.setLocation(x, y);
		gridboard[y][x] = tile;
		
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int y=0; y<getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x=0; x<getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}

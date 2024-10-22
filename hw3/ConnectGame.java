package hw3;

import java.util.ArrayList;


import java.util.Random;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;
/**
 * @author issmale bekri
 */

/**
 * Class that models a game.
 */
public class ConnectGame {
	/**
	 * variable that prints out the dialogListener
	 */
	private ShowDialogListener dialogListener;
	/**
	 * variable that updates and prints out the player's score
	 */
	private ScoreUpdateListener scoreListener;
	/**
	 * integer that holds the width
	 */
	private int width;
	/**
	 * integer that holds the height
	 */
	private int height;
	/**
	 * integer that holds the minimum tile
	 */
	private int minTile;
	/**
	 * integer that holds the maximum tile
	 */
	private int maxTile;
	/**
	 * integer that holds a random number
	 */
	private int random;
	/**
	 * 2D array of tiles
	 */
	private Tile[][] tile;
	/**
	 * boolean that checks if the tile is selected
	 */
	private boolean isSelected = false;
	/**
	 * creates the grid
	 */
	private Grid grid;
	/**
	 * long value that holds the player's score
	 */
	private long playerScore = 0;
	/**
	 * array list that holds the selected Tiles
	 */
	private ArrayList<Tile> selectedTiles ;
	/**
	 * creates random
	 */
	private Random rand = new Random();
	/**
	 * keeps track of the last selected tile
	 */
	private Tile lastSelectedTile;
	/**
	 * keeps track of the first selected tile
	 */
	private Tile startTile;
	
	
	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
		grid = new Grid(width,height);
	    this.width = width;
		this.height = height;
		minTile = min;
		maxTile = max;
		tile = new Tile[height][width];
		selectedTiles = new ArrayList<>(); 
		this.rand = rand;
		
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		random = rand.nextInt(maxTile - minTile) + minTile;
		Tile tile = new Tile(random);
		return tile;
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		
		for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            Tile newTile = getRandomTile();
	                grid.setTile(newTile, col, row);

	        }
	    }
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
	
		int x1 = t1.getX();
	    int y1 = t1.getY();
	    int x2 = t2.getX();
	    int y2 = t2.getY();

	    return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		Tile selectedTile = grid.getTile(x, y);
	    
	    if (selectedTiles.size() > 0) {
	        return false;
	    }
	    
	    startTile = selectedTile;
	    lastSelectedTile = selectedTile;
	    selectedTiles.add(selectedTile);
	    selectedTile.setSelect(true);
	    isSelected = true;
	    
	    return true;
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) {
		
		if(selectedTiles.size() > 0 && x < grid.getHeight() && y < grid.getWidth() ){
			Tile lastTile = selectedTiles.get(selectedTiles.size() - 1);
			Tile selectedTile = grid.getTile(x, y);
		    boolean isAdjacent = isAdjacent(selectedTile,lastTile);
			if(selectedTiles.size() == 1 && selectedTile.getLevel() == selectedTiles.get(0).getLevel() && isAdjacent) {
				selectedTiles.add(selectedTile);
				selectedTile.setSelect(true);
			}
			else if (selectedTiles.size() > 1) {
				boolean levelsMatch = (selectedTile.getLevel() == lastTile.getLevel()) || (selectedTile.getLevel() == lastTile.getLevel() +1);
				if((levelsMatch && isAdjacent) && selectedTile.isSelected() == false) {
					selectedTiles.add(selectedTile);
					selectedTile.setSelect(true);
				}
				else if((selectedTiles.size() >= 2) && selectedTile == selectedTiles.get(selectedTiles.size() - 2)) {
						unselect(lastTile.getX(),lastTile.getY());
						
				}
			}
		}
	}
	
	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		long addScore = 0;
		Tile selectedTile = grid.getTile(x, y);	
		if (selectedTiles.size() > 1) {
			if (selectedTile.getLevel() == selectedTiles.get(selectedTiles.size() - 1).getLevel()
					|| selectedTile.getLevel() == selectedTiles.get(selectedTiles.size() - 1).getLevel() + 1) {
				if (selectedTiles.contains(selectedTile) == false) {
					selectedTiles.add(selectedTile);
					selectedTile.setSelect(true);
				}
			}
		}
		if (selectedTile.isSelected()) {
			if (grid.getTile(x, y) == selectedTiles.get(selectedTiles.size() - 1)) {
				if (selectedTiles.size() == 1) {
					for (Tile tiles : selectedTiles) {
						tiles.setSelect(false);
					}
					selectedTiles.clear();
					return true;
				} else if (selectedTiles.size() >= 2) {
					for (Tile tiles : selectedTiles) {
						addScore += Math.pow(2, tiles.getLevel());
						tiles.setSelect(false);
					}
					upgradeLastSelectedTile();
					playerScore += addScore;
					setScore(playerScore);
					scoreListener.updateScore(playerScore);
					selectedTiles.clear();
					dropSelected();
				}
				return true;
			}
		}
		return false;
	}

	

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		
		lastSelectedTile = selectedTiles.get(selectedTiles.size() - 1);	
		lastSelectedTile.setLevel(lastSelectedTile.getLevel() + 1);	
		unselect(lastSelectedTile.getY(), lastSelectedTile.getX());
		int level = lastSelectedTile.getLevel();	
		lastSelectedTile.setSelect(false);	
	    if (level > maxTile) {
	    	maxTile++;
	    	int highestValue = (int) Math.pow(2, maxTile);
	    	int lowestValue = (int) Math.pow(2, minTile);
	    	minTile++;
	    	dialogListener.showDialog("New block " + highestValue + ", removing blocks " + lowestValue );
	    }
	}
	
	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {

	    return selectedTiles.toArray(new Tile[selectedTiles.size()]);
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {

		int width = grid.getWidth();
	    int height = grid.getHeight();

	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            Tile tile = grid.getTile(j, i);
	            if (tile.getLevel() == level) {
	                for (int k = i - 1; k >= 0; k--) {
	                    Tile toReplace = grid.getTile(j, k);
	                    grid.setTile(toReplace, j, k + 1);
	                }
	                grid.setTile(getRandomTile(), j, 0);
	                break;
	            }
	        }
	    }
	}


	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {

		for(int i = 0; i < grid.getWidth(); i++) {
			for(int j = 0; j < grid.getHeight(); j++) {
				if(selectedTiles.contains(grid.getTile(i, j))) {
					grid.setTile(getRandomTile(), i, j);
					if(j == 0) {				
					}
					else if(j >= 1) {
						grid.setTile(grid.getTile(i, j-1), i, j);
					}
				}
			}
		}
	}
	







	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		grid.getTile(x, y).setSelect(false);
		selectedTiles.remove(grid.getTile(x, y));
	}
	

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		return playerScore;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		return minTile;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		return maxTile;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		playerScore = score;

	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
	
		this.grid = grid;
     }

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		minTile = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		maxTile = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		try {
			GameFileUtil.load(filePath, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

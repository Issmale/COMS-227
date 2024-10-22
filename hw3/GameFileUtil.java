package hw3;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import api.Tile;
/**
 * @author issmale bekri
 */

/**
 * Utility class with static methods for saving and loading game files.
 */
public class GameFileUtil {
	/**
	 *
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			Grid grid = game.getGrid();
			int width = grid.getWidth();
			int height = grid.getHeight();
			int minTile = game.getMinTileLevel();
			int maxTile = game.getMaxTileLevel();
			long playerScore = game.getScore();
			writer.write(width + " " + height + " " + minTile + " " + maxTile + " " + playerScore);
			for(int i = 0; i < height; i++)
			{
				writer.write('\n');
				for(int j = 0; j < width; j++)
				{
					Tile tile = grid.getTile(j, i);
					if(tile != null && j == width - 1)
					{
						writer.write(tile.getLevel() + "");
					}
					else if(tile != null)
					{
						writer.write(tile.getLevel() + " ");
					}
					else 
					{
						writer.write("0 ");
					}
				}
			}
			
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	        
	}

	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath, ConnectGame game) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String[] str = reader.readLine().split(" ");
	            int width = Integer.parseInt(str[0]);
	            int height = Integer.parseInt(str[1]);
	            int minTile = Integer.parseInt(str[2]);
	            int maxTile = Integer.parseInt(str[3]);
	            int playerScore = Integer.parseInt(str[4]);
	            Grid grid = new Grid(width, height);
	            game.setMinTileLevel(minTile);
	            game.setMaxTileLevel(maxTile);
	            game.setScore(playerScore);
	            
	            grid = new Grid(width, height);
	            for (int row = 0; row < height; row++) {
	                String[] str2 = reader.readLine().split(" ");
	                for (int col = 0; col < width; col++) {
	                    int level = Integer.parseInt(str2[col]);
	                    Tile tile = new Tile(level);
	                    grid.setTile(tile,col, row);
	                }
	            }
	            game.setGrid(grid);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
package JamesPackage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import items.Item;

/**
 * This class represents each map in the world. Each map is represented by a
 * series of layers, each layer being split into 32x32 pixel tiles. The map must
 * also know about all the possible items that can be located on itself.
 * 
 * @author James
 *
 */
public class Map {
	// HashMap of the Items located on the map and their locations
	private HashMap<Item, Location> items;

	// Width of a individual tile
	public static final int tileWidth = 32;

	// Height of a individual tile
	public static final int tileHeight = 32;

	// The width of the map
	private int width;

	// The height of the map
	private int height;

	// 2D array of all the images that make up the backGround layer
	private ArrayList<ArrayList<BufferedImage>> backgroundLayer;

	// 2D array of all the images that make up the collision layer
	private ArrayList<ArrayList<BufferedImage>> collisionLayer;

	// 2D array of all the images that make up the item layer
	private ArrayList<ArrayList<BufferedImage>> itemLayer;

	// 2D array of all the images that make up the enviromental layer
	private ArrayList<ArrayList<BufferedImage>> enviromentalLayer;

	// The current player
	// Player

	public Map(String name, ArrayList<Item> items) {

	}

	/**
	 * This method decides whether a position on the map can be moved over by a
	 * entity
	 * 
	 * @param x
	 * @param y
	 */
	public boolean canMove(int x, int y) {
		return false;
	}

	/**
	 * This method returns the 2D ArrayList of images that make up the background
	 * 
	 * @return A 2D ArrayList of buffered images
	 */
	public ArrayList<ArrayList<BufferedImage>> getBackgroundLayer() {
		return this.backgroundLayer;
	}

	/**
	 * This method returns a 2D ArrayList of images that make up the item layer
	 * 
	 * @return A 2D ArrayList of buffered images
	 */
	public ArrayList<ArrayList<BufferedImage>> getImageLayer() {
		return this.itemLayer;
	}

	/**
	 * This method returns the enviroment on a given tile specified by a x and y
	 * value, returns null if there is no item. Returns an exception if the x or y
	 * position is invalid.
	 * 
	 * @param x
	 * @param y
	 * @return The enviroment on the tile at x,y
	 */
	public Enviroment onEnviromentTile(int x, int y) {
		return null;
	}

	/**
	 * This method returns whether there is a item on a given spot
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean itemAtTile(int x, int y) {
		return false;
	}

	/**
	 * This method returns the item located at position x,y. Returns null if there
	 * is no item. Returns an exception if the x or y position is invalid.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Item itemAt(int x, int y) {
		return null;
	}

	/**
	 * This method returns the image at a given x and y position. Returns an
	 * exception if the x and y position is invalid.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage imageAt(int x, int y) {
		return null;
	}

	/**
	 * This method takes a x and y location and rounds it to an absolute
	 * position(tile), e.g. Tile 2.5 does not exist so it is rounded down to tile 2
	 * which does exist. Throws an error if the given x and y is invalid.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private Location positionOnMap(int x, int y) {
		return null;
	}

}

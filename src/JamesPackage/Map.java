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

	// All the doors located on this map
	private ArrayList<Door> doors;

	public Map(String name, ArrayList<Item> items, ArrayList<String> doors) {

	}

	/**
	 * This method takes an ArrayList of items and assigns them random locations on
	 * this map, it also gets the image associated with the item and places the
	 * image in the image layer at the give location
	 */
	private void randomItemPlacement(ArrayList<Item> item) {

	}

	/**
	 * This method splits each layer into the 32x32 tiles and inserts them into the
	 * map field, as well as sets the width and height of the map.
	 * 
	 * @param name
	 *            The name of the map
	 */
	private void loadMap(String name) {

	}

	/**
	 * Initialis's all the entities on the map
	 */
	private void loadEntities() {

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
	public boolean isItem(int x, int y) {
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

}

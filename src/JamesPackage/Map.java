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

	private HashMap<Item, Location> items;
	private ArrayList<ArrayList<BufferedImage>> map;
	private int width;
	private int height;
	private int backgroundLayer = 2;
	private int collisionLayer = 0;
	private int itemLayer = 3;
	private int enviromentalLayer = 1;

	public Map(String name, ArrayList<Item> items) {

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
	 * map field, as well as sets the width and height of the map
	 * 
	 * @param name
	 *            The name of the map
	 */
	private void loadMap(String name) {

	}

	/**
	 * This method decides whether a position on the map can be moved over by a
	 * entity
	 * 
	 * @param x
	 * @param y
	 */
	public void canMove(int x, int y) {

	}

	/**
	 * @return An ArrayList of the images that need to be rendered
	 */
	public ArrayList<ArrayList<BufferedImage>> getImagesToRender() {
		return new ArrayList<ArrayList<BufferedImage>>(
				Arrays.asList(this.map.get(this.backgroundLayer), this.map.get(this.itemLayer)));
	}

	public void getBackgroundTile() {

	}

}

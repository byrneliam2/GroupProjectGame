package map;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import items.DoorItem;
import items.Item;
import npc.NPC;
import player.Bullet;
import player.Player;

/**
 * This class represents each map in the world. Each map is represented by a
 * series of layers, each layer being split into 32x32 pixel tiles. The map must
 * also know about all the possible items that can be located on itself.
 *
 * @author James
 *
 */
public class Map {
	// Width of a individual tile
	public static final int tileWidth = 32;

	// Height of a individual tile
	public static final int tileHeight = 32;

	// HashMap of the Items located on the map and their locations
	private HashMap<Item, Location> items;

	private ArrayList<NPC> NPCS;

	// The width of the map in tiles
	private int width;

	// The height of the map in tiles.
	private int height;

	// 2D array of all the images that make up the backGround layer
	private String backgroundLayer;

	// 2D array of all the images that make up the collision layer
	private ArrayList<ArrayList<BufferedImage>> collisionLayer;

	// 2D array of all the images that make up the enviromental layer
	private ArrayList<ArrayList<BufferedImage>> enviromentalLayer;

	// The current player
	private Player currentPlayer;

	// The name of the map
	private String name;

	public Map(String name, HashMap<Item, Location> items, ArrayList<NPC> NPCS) {
		this.name = name;
		this.items = items;
		this.backgroundLayer = name;
		this.NPCS = NPCS;

	}

	/**
	 * Checks whether the bullet has hit either the player or any NPC's
	 *
	 * @param b
	 *            the bullet to check.
	 * @return true if the bullet hit a player/npc false otherwise.
	 */
	public boolean checkBulletHit(Bullet b) {
		// for each entity{
		// check bullet doesnt belong to entity use bullet.getOwner()
		// check whether bullet is touching entity
		// if it is, then return true, and call the takeDamage() method on the entity
		return false;
	}

	public DoorItem getDoor(Rectangle boundingBox) {
		// returns the door touched by the bounding box, or null if no door is touched
		// by bounding box
		return null;
	}

	public Item getClosestItem(Ellipse2D rangeCircle) {
		// returns the closest item that is within the rangeCircle
		// returns null if no items in the range circle

		return null;
	}

	public void placeItem(Item i, int x, int y) {
		// drops the given item onto the map at the given location
		// x and y are pixel locations rather than tile locations.
	}

	public void removeItem(Item i) {
		// removes the given item from the map.
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
	 * This method returns the filename of the image that makes up the background
	 *
	 * @return A filename of the background image
	 */
	public String getBackgroundLayer() {
		return this.backgroundLayer;
	}

	/**
	 * This method returns a HashMap of images that make up the item layer
	 *
	 * @return A HashMap of buffered images
	 */
	public HashMap<Item, Location> getItems() {
		return this.items;
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

	/**
	 * This method returns all the NPC's on the map
	 *
	 * @return A ArrayList of NPC
	 */
	public ArrayList<NPC> getNPCS() {
		return this.NPCS;
	}

	/**
	 * This method returns the map name
	 *
	 * @return The map name
	 */
	public String getMapName() {
		return this.name;
	}

}

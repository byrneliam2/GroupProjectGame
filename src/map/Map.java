package map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import gfx.ImageLoader;
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
	// Size of a individual tile
	public static final int tileSize = 32;

	// HashMap of the Items located on the map and their locations
	private HashMap<Item, Point> items;

	private ArrayList<NPC> NPCS;

	// The width of the map in tiles
	private int width;

	// The height of the map in tiles.
	private int height;

	// 2D array of all the images that make up the backGround layer
	private String backgroundLayer;

	// 2D array of all the images that make up the collision layer
	private ArrayList<ArrayList<Integer>> collisionLayer;

	// 2D array of all the images that make up the enviromental layer
	private ArrayList<ArrayList<BufferedImage>> enviromentalLayer;

	// The current player
	private Player currentPlayer;

	// The name of the map
	private String name;

	// Doors on the current map
	HashMap<DoorItem, Point> Doors;

	public Map(String name, HashMap<Item, Point> items, ArrayList<NPC> NPCS, HashMap<DoorItem, Point> doors) {
		this.name = name;
		this.items = items;
		this.backgroundLayer = name;
		this.NPCS = NPCS;
		this.Doors = doors;
		BufferedImage colLayer = this.loadImage(this.name, "collisionLayer");
		this.collisionLayer = this.loadColLayers(colLayer);
		BufferedImage enviromentLayer = this.loadImage(this.name, "collisionLayer");
		this.enviromentalLayer = this.breakUpImageIntoTiles(enviromentLayer);
	}

	/**
	 * This method locates a map layer in the assets folder of the map class using a
	 * mapName and a layer
	 * 
	 * @param mapName
	 * @param layer
	 * @return BufferedImage representing the layer of a given map
	 * @throws BadMapImageException
	 */
	private BufferedImage loadImage(String mapName, String layer) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Map.class.getResource("..map/assets/mapImages/" + mapName + layer));
			if (img.getHeight() % 2 > 0 || img.getWidth() % 2 > 0) {
				throw new BadMapImageException(
						"The image you are trying to load does not have the correct Dimensions, Dimensions should be a factor of 32, the Global tile size.");
			}
		} catch (IOException | BadMapImageException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return img;
	}

	/**
	 * This method takes a buffered image representing the collision layer and
	 * converts it into a 2D array of integers. Each integer representing whether
	 * the buffered image was black or not.
	 * 
	 * @param colLayer
	 * @return 2D ArrayList<Integer>
	 */
	private ArrayList<ArrayList<Integer>> loadColLayers(BufferedImage colLayerUnbroken) {
		ArrayList<ArrayList<BufferedImage>> colLayer = this.breakUpImageIntoTiles(colLayerUnbroken);

		this.width = colLayer.get(0).size();
		this.height = colLayer.size();
		ArrayList<ArrayList<Integer>> collLayer = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < this.height; i++) {
			collLayer.add(new ArrayList<Integer>(width));
		}

		for (int col = 0; col < this.height; col++) {
			for (int row = 0; row < this.width; row++) {
				Color c = new Color(colLayer.get(col).get(row).getRGB(1, 1));
				if (c.getGreen() == 0 && c.getRed() == 0 && c.getBlue() == 0) {
					collLayer.get(col).add(1);
				} else {
					collLayer.get(col).add(0);
				}

			}
		}
		return collLayer;
	}

	/**
	 * This method breaks up a bufferedImage into sub images based off the TileSize.
	 * 
	 * @param colLayer
	 * @return A 2D array of BufferedImages
	 */
	private ArrayList<ArrayList<BufferedImage>> breakUpImageIntoTiles(BufferedImage colLayer) {
		ArrayList<ArrayList<BufferedImage>> layer = new ArrayList<ArrayList<BufferedImage>>();
		for (int initY = 0; initY < this.height; initY += 32) {
			layer.add(new ArrayList<BufferedImage>());
		}

		for (int y = 0; y < this.height; y += 32) {
			for (int x = 0; x < this.width; x += 32) {
				BufferedImage newTile = colLayer.getSubimage(x, y, Map.tileSize, Map.tileSize);
				int posY = y / 32;
				layer.get(posY).add(newTile);
			}
		}
		return layer;
	}

	/**
	 * Checks whether the bullet has hit either the player or any NPC's
	 *
	 * @param b
	 *            the bullet to check.
	 * @return true if the bullet hit a player/npc false otherwise.
	 */
	public boolean checkBulletHit(Bullet b) {
		String bulletOwner = b.getOwner().getName();
		double bulletX = b.getX();
		double bulletY = b.getY();
		Point bulletLocation = new Point();
		bulletLocation.setLocation(bulletX, bulletY);
		for (NPC entity : this.NPCS) {
			if (!entity.getName().equals(bulletOwner)) {
				if (entity.getBoundingBox().contains(bulletLocation)) {
					entity.takeDamage();
					return true;
				}
			}
		}
		if (!this.currentPlayer.getName().equals(bulletOwner)) {
			if (this.currentPlayer.getBoundingBox().contains(bulletLocation)) {
				this.currentPlayer.takeDamage();
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns the door touched by a given bounding box, if there is no
	 * door returns null
	 * 
	 * @param boundingBox
	 * @return
	 */
	public DoorItem getDoor(Rectangle boundingBox) {
		for (DoorItem d : this.Doors.keySet()) {
			if (boundingBox.contains(this.Doors.get(d))) {
				return d;
			}
		}
		return null;
	}

	/**
	 * This method returns the item that is closest to the center of a circle.
	 * Returns null if there are no items in the circle
	 * 
	 * @param rangeCircle
	 * @return
	 */
	public Item getClosestItem(Ellipse2D rangeCircle) {

		HashMap<Item, Point> closeItems = new HashMap<Item, Point>();
		for (Item item : this.items.keySet()) {
			if (rangeCircle.contains(this.items.get(item))) {
				closeItems.put(item, this.items.get(item));
			}
		}
		double centerX = rangeCircle.getCenterX();
		double centerY = rangeCircle.getCenterY();
		Item closest = null;
		double closestDistanceFromCenter = Double.MAX_VALUE;
		for (Item item : closeItems.keySet()) {
			double itemX = this.items.get(item).getX();
			double itemY = this.items.get(item).getY();

			double distX = Math.abs(centerX - itemX);
			double distY = Math.abs(centerY - itemY);
			if (distX < closestDistanceFromCenter) {
				closestDistanceFromCenter = distX;
				closest = item;
			}
			if (distY < closestDistanceFromCenter) {
				closestDistanceFromCenter = distY;
				closest = item;
			}
		}
		return closest;
	}

	/**
	 * This method drops a given item onto a x,y spot on the Map by inserting the
	 * new item and its pint into the HashMap of items located on this Map.
	 * 
	 * @param i
	 * @param x
	 * @param y
	 */
	public void placeItem(Item i, int x, int y) {
		Point toDrop = this.positionOnMap(x, y);
		this.items.put(i, toDrop);
	}

	/**
	 * This method removes a given item from the map by removing the item from the
	 * Maps HashMap of items.
	 * 
	 * @param i
	 * @return Whether or not the item was removed
	 */
	public boolean removeItem(Item i) {
		if (!this.items.containsKey(i)) {
			return false;
		}
		this.items.remove(i);
		return true;
	}

	/**
	 * This method decides whether a position on the map can be moved over by a
	 * entity
	 *
	 * @param x
	 * @param y
	 */
	public boolean canMove(int x, int y) {
		Point mapPos = this.positionOnMap(x, y);
		if (this.collisionLayer.get((int) mapPos.getY()).get((int) mapPos.getX()) == 1) {
			return false;
		} else {
			return true;
		}
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
	 * This method returns a HashMap of all the items located on this map
	 *
	 * @return A HashMap of buffered images
	 */
	public HashMap<Item, Point> getItems() {
		return this.items;
	}

	/**
	 * This method returns the environment on a given tile specified by a x and y
	 * value, returns null if there is no item. Returns an exception if the x or y
	 * position is invalid.
	 *
	 * @param x
	 * @param y
	 * @return The environment on the tile at x,y
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
	public boolean itemAtTile(double x, double y) {
		Point pos = this.positionOnMap(x, y);
		for (Item itm : this.items.keySet()) {
			if (this.items.get(itm).getX() == pos.getX() && this.items.get(itm).getY() == pos.getY()) {
				return true;
			}
		}
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
	public Item itemAt(double x, double y) {
		Point pos = this.positionOnMap(x, y);
		for (Item itm : this.items.keySet()) {
			if (this.items.get(itm).getX() == pos.getX() && this.items.get(itm).getY() == pos.getY()) {
				return itm;
			}
		}
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
	private Point positionOnMap(double x, double y) {
		return new Point((int) x, (int) y);
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

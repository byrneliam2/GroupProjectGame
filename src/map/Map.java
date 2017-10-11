package map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import common.player.IPlayer;
import game.Game;
import gfx.ImageLoader;
import gfx.ImageUtilities;
import items.DoorItem;
import common.items.Item;
import common.map.IMap;
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
public class Map implements IMap {
	/** pixel size of a individual tile */
	public static final int tileSize = 60;

	/** HashMap of the Items located on the map and their locations */
	private List<Item> items;

	/** List of all NPC's located on this map */
	private List<NPC> NPCS;

	/** The width of the map in tiles */
	private int width;

	/** The height of the map in tiles. */
	private int height;

	/** 2D array of all the images that make up the backGround layer */
	private String backgroundLayer;

	/** 2D array of all the images that make up the collision layer */
	private ArrayList<ArrayList<Integer>> collisionLayer;

	// 2D array of all the images that make up the environmental layer
	// Black(1) is for death
	// Green(2) is for mud
	// Red(3) is for fire
	// Blue(4) is for mist
	private ArrayList<ArrayList<Integer>> environmentalLayer;

	/** The current player */
	private IPlayer currentPlayer;

	/** The name of the map */
	private String name;

	/** Doors on the current map */
	List<DoorItem> doors;

	public Map(String name, IPlayer player, List<Item> items, ArrayList<NPC> NPCS, List<DoorItem> doors) {
		this.name = name;
		this.items = items;
		this.currentPlayer = player;
		player.setMap(this);// sets the player up
		this.backgroundLayer = name;
		this.NPCS = NPCS;
		this.doors = doors;
		this.loadAllLayers(1920, 1080);
		// sets the npc's up, note you'll still have to call startMapNPC's() to start
		// them moving
		if (Game.DEV_MODE && !name.equals("Map16")) {
			NPCS.clear();
		} else {
			for (NPC npc : NPCS) {
				npc.setMap(this);
			}
		}
		this.placeAllItems(this.items);

	}

	/**
	 * Pauses all of the map's npc's so that they don't move. Useful to call when
	 * the player changes maps.
	 */
	public void pauseMapNPCs() {
		for (NPC npc : NPCS) {
			npc.stop();
		}
	}

	/**
	 * Starts all the npc's moving in this map.
	 */
	public void startMapNPCs() {
		for (NPC npc : NPCS) {
			npc.start();
		}
	}

	/**
	 * Removes the specified npc from the map and from the game.
	 *
	 * @param toBeRemoved
	 *            the npc to be gone
	 */
	public void removeNPC(NPC toBeRemoved) {
		NPCS.remove(toBeRemoved);
	}

	/**
	 * This method takes the collision and environment layers and scales them to the
	 * correct size, then loads them.
	 *
	 * @param scaleX
	 * @param scaleY
	 * @param tileSize
	 */
	private void loadAllLayers(int newWidth, int newHeight) {
		BufferedImage colLayer = ImageLoader.image("MapImages", this.name + "Collision", true);
		this.width = colLayer.getWidth() / 32;
		this.height = colLayer.getHeight() / 32;
		colLayer = ImageUtilities.scale(colLayer, newWidth, newHeight);
		this.collisionLayer = this.loadColLayers(colLayer);

		BufferedImage EnvLayer = ImageLoader.image("MapImages", this.name + "Environment", true);
		EnvLayer = ImageUtilities.scale(EnvLayer, newWidth, newHeight);
		this.environmentalLayer = this.loadEnvLayers(EnvLayer);

	}

	private void placeAllItems(List<Item> itms) {
		if (itms == null) {
			return;
		}
		for (Item i : itms) {
			if (i.getX() == 0 && i.getY() == 0) {
				boolean placed = false;

				while (!placed) {
					int width = (int) (Math.random() * 31) + 1;
					int height = (int) (Math.random() * 17) + 1;
					if (this.canMove(width * Map.tileSize, height * Map.tileSize)
							&& this.onEnvironmentTile(width * Map.tileSize, height * Map.tileSize) == null) {
						placed = true;
						i.setX(width * Map.tileSize);
						i.setY(height * Map.tileSize);
					}
				}
				placed = false;
			}
		}
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
	 * This method takes a buffered image representing the environment layer and
	 * converts it into a 2D array of integers. Each integer representing a type of
	 * environment
	 *
	 * @param EnviroLayerUnbroken
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> loadEnvLayers(BufferedImage EnviroLayerUnbroken) {
		ArrayList<ArrayList<BufferedImage>> EnvLayer = this.breakUpImageIntoTiles(EnviroLayerUnbroken);

		ArrayList<ArrayList<Integer>> EnvironmentalLayer = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < this.height; i++) {
			EnvironmentalLayer.add(new ArrayList<Integer>(width));
		}

		for (int col = 0; col < this.height; col++) {
			for (int row = 0; row < this.width; row++) {
				Color c = new Color(EnvLayer.get(col).get(row).getRGB(1, 1));
				if (c.getGreen() == 0 && c.getRed() == 0 && c.getBlue() == 0) {// Black(1) is for death environment
					EnvironmentalLayer.get(col).add(1);
				} else if (c.getGreen() == 255 && c.getRed() == 0 && c.getBlue() == 0) {// Green(2) is for mud
					// environment
					EnvironmentalLayer.get(col).add(2);
				} else if (c.getGreen() == 0 && c.getRed() == 255 && c.getBlue() == 0) {// Red(3) is for fire
																						// environment
					EnvironmentalLayer.get(col).add(3);
				} else if (c.getGreen() == 0 && c.getRed() == 0 && c.getBlue() == 255) {// Blue(4) is for mist
																						// environment
					EnvironmentalLayer.get(col).add(4);
				} else {
					EnvironmentalLayer.get(col).add(0);// (0) is for no environment
				}

			}
		}
		return EnvironmentalLayer;
	}

	/**
	 * This method breaks up a bufferedImage into sub images based off the TileSize.
	 *
	 * @param colLayer
	 * @return A 2D array of BufferedImages
	 */
	private ArrayList<ArrayList<BufferedImage>> breakUpImageIntoTiles(BufferedImage colLayer) {
		int widthUnbroken = colLayer.getWidth();
		int heightUnbroken = colLayer.getHeight();
		ArrayList<ArrayList<BufferedImage>> layer = new ArrayList<ArrayList<BufferedImage>>();
		for (int initY = 0; initY < heightUnbroken; initY += Map.tileSize) {
			layer.add(new ArrayList<BufferedImage>());
		}
		for (int y = 0; y < heightUnbroken; y += Map.tileSize) {
			for (int x = 0; x < widthUnbroken; x += Map.tileSize) {
				BufferedImage newTile = colLayer.getSubimage(x, y, Map.tileSize, Map.tileSize);
				int posY = y / Map.tileSize;
				layer.get(posY).add(newTile);
			}
		}
		return layer;
	}

	/**
	 * Checks whether the bullet has hit either the player or any NPC's
	 *
	 * @param bheight
	 *            the bullet to check.
	 * @return true if the bullet hit a player/npc false otherwise.
	 */
	public boolean checkBulletHit(Bullet b) {
		double bulletX = b.getX();
		double bulletY = b.getY();
		Point bulletLocation = new Point();
		bulletLocation.setLocation(bulletX, bulletY);

		if (b.getOwner() == this.currentPlayer) {
			for (NPC npc : this.NPCS) {// check if any npc's are hit by the bullet
				if (npc.getBoundingBox().contains(bulletLocation)) {
					npc.takeDamage();
					return true;
				}
			}
		} else if (this.currentPlayer.getBoundingBox().contains(bulletLocation)) {
			this.currentPlayer.takeDamage();
			return true;
		}

		return false;
	}

	/**
	 * This method returns the door touched by a given bounding box, if there is no
	 * door returns null. Assumes a door is one tile block in size.
	 *
	 * @param boundingBox
	 * @return
	 */
	public DoorItem getDoor(Rectangle.Double boundingBox) {
		if (doors == null)
			return null;
		for (DoorItem d : this.doors) {
			Point doorPoint = d.getCentrePoint();
			if (boundingBox.contains(doorPoint)) {
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
		if (items == null)
			return null;

		Item closest = null;
		double ClosestDistance = 0;
		for (Item item : this.items) {
			// center point of item is used.
			Point itm = new Point(item.getX() + Map.tileSize / 2, item.getY() + Map.tileSize / 2);
			if (rangeCircle.contains(itm)) {
				double xDist = (itm.getX() - rangeCircle.getCenterX());
				double yDist = (itm.getY() - rangeCircle.getCenterY());
				double dist = Math.hypot(xDist, yDist);// actual distance to Item

				if (closest == null || dist < ClosestDistance) {
					closest = item;
					ClosestDistance = dist;
				}
			}
		}
		return closest;
	}

	/**
	 * This method drops a given item onto a x,y spot on the Map by inserting the
	 * new item into this maps ArrayList of items.
	 *
	 * @param i
	 * @param x
	 * @param y
	 */
	public void placeItem(Item i, int x, int y) {
		this.items.add(i);
		i.setX(x);// might be unnecessary TODO.
		i.setY(y);
	}

	/**
	 * This method removes a given item from the map by removing the item from the
	 * List of items.
	 *
	 * @param i
	 * @return Whether or not the item was removed
	 */
	public boolean removeItem(Item i) {
		return this.items.remove(i);
	}

	/**
	 * This method decides whether a position on the map can be moved over by a
	 * entity, if the x and y is out of the map returns false
	 *
	 * @param x
	 * @param y
	 */
	public boolean canMove(int x, int y) {
		if (x < 0 || y < 0 || x >= this.width * Map.tileSize || y >= this.height * Map.tileSize)
			return false;
		x = (int) (x / Map.tileSize);
		y = (int) (y / Map.tileSize);

		if (this.collisionLayer.get(y).get(x) == 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method decides whether a position on the map can be moved over by a
	 * rectangle, using the rectangles four corners
	 *
	 * @param r
	 * @return
	 */
	public boolean canMove(Rectangle.Double r) {

		// Top Left
		double posX = r.getX();
		double posY = r.getY();

		if (posX < 0 || posY < 0 || posX >= (this.width * Map.tileSize) || posY >= (this.height * Map.tileSize)) {
			return false;
		}
		if (this.collisionLayer.get((int) (posY / Map.tileSize)).get((int) (posX / Map.tileSize)) == 1) {
			return false;
		}
		// Top right
		posX = posX + r.getWidth();
		if (posX >= (this.width * Map.tileSize)) {
			return false;
		}
		if (this.collisionLayer.get((int) (posY / Map.tileSize)).get((int) (posX / Map.tileSize)) == 1) {
			return false;
		}

		// Bottom right
		posY = posY + r.getHeight();
		if (posY >= (this.height * Map.tileSize)) {
			return false;
		}

		if (this.collisionLayer.get((int) (posY / Map.tileSize)).get((int) (posX / Map.tileSize)) == 1) {
			return false;
		}

		// Bottom Left
		posX = posX - r.getWidth();
		if ((posX) < 0) {
			return false;
		}

		if (this.collisionLayer.get((int) (posY / Map.tileSize)).get((int) (posX / Map.tileSize)) == 1) {
			return false;
		}
		return true;

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
	public List<Item> getItems() {
		return this.items;
	}

	/**
	 * This method returns the environment on a given tile specified by a x and y
	 * value, returns null if there is no environment. Returns an exception if the x
	 * or y position is invalid. //Black(1) is for death //Green(2) is for mud
	 * //Blue(4) is for mist //Red(3) is for fire
	 *
	 * @param x
	 * @param y
	 * @return The environment on the tile closest to x,y
	 */
	public Environment onEnvironmentTile(int x, int y) {
		if (x < 0 || y < 0 || x > this.width * Map.tileSize || y > this.height * Map.tileSize) {
			return null;
		}
		x = (int) (x / tileSize);
		y = (int) (y / tileSize);
		int environment = this.environmentalLayer.get(y).get(x);
		if (environment == 0) {
			return null;
		} else if (environment == 1) {
			return Environment.DEATH;
		} else if (environment == 2) {
			return Environment.MUD;
		} else if (environment == 3) {
			return Environment.FIRE;
		} else {
			return Environment.MIST;
		}
	}

	/**
	 * This method returns all the NPC's on the map
	 *
	 * @return A ArrayList of NPC
	 */
	public List<NPC> getNPCs() {
		return this.NPCS;
	}

	public DoorItem getDoor(int doorID) {
		for (DoorItem d : doors) {
			if (d.getDoorID() == doorID) {
				return d;
			}
		}
		return null;
	}

	/**
	 * This method returns the map name
	 *
	 * @return The map name
	 */
	public String getName() {
		return this.name;
	}

}

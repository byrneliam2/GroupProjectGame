package map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.player.IPlayer;
import game.Game;
import gfx.ImageLoader;
import gfx.ImageUtilities;
import items.DoorItem;
import common.items.Item;
import common.map.IMap;
import common.map.IWorld;
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
public class Map implements IMap, Serializable {
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

	/** The name of the map */
	private String name;

	private IWorld world;

	private IPlayer currentPlayer;

	/** Doors on the current map */
	List<DoorItem> doors;

	public Map(String name, IPlayer currentPlayer, List<Item> items, ArrayList<NPC> NPCS, List<DoorItem> doors) {
		this.name = name;
		this.items = items;
		this.backgroundLayer = name;
		this.NPCS = NPCS;
		this.doors = doors;
		this.loadAllLayers(1920, 1080);
		this.currentPlayer = currentPlayer;

		// sets the npc's up, note you'll still have to call startMapNPC's() to start
		// them moving
		if (Game.DEV_MODE && !name.equals("Map16")) {
			// if dev mode, remove all npc's except the boss
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
		toBeRemoved.stop();
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
		colLayer = ImageUtilities.scale(colLayer, newWidth, newHeight);

		BufferedImage EnvLayer = ImageLoader.image("MapImages", this.name + "Environment", true);
		EnvLayer = ImageUtilities.scale(EnvLayer, newWidth, newHeight);

		this.width = colLayer.getWidth() / Map.tileSize;
		this.height = colLayer.getHeight() / Map.tileSize;

		LayerLoader loader = new LayerLoader();
		this.collisionLayer = loader.translateImageToCollisionArray(colLayer);
		this.environmentalLayer = loader.translateImageToEnvironmentArray(EnvLayer);

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
			Rectangle doorRect = d.getEnterBox();
			if (boundingBox.intersects(doorRect)) {
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
	 * new item into this maps List of items.
	 *
	 * @param i
	 * @param x
	 * @param y
	 */
	public void placeItem(Item i, int x, int y) {
		this.items.add(i);
		i.setX(x);
		i.setY(y);
	}

	/**
	 * This method drops a given door onto a x,y spot on the Map by inserting the
	 * new door into this maps List of doors.
	 *
	 * @param i
	 * @param x
	 * @param y
	 */
	public void placeDoorItem(DoorItem i, int x, int y) {
		this.doors.add(i);
		i.setX(x);
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

	public IWorld getWorld() {
		return world;
	}

	public void setWorld(IWorld world) {
		this.world = world;
	}

}

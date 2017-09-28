package map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private List<Item> items;

	private List<NPC> NPCS;

	// The width of the map in tiles
	private int width;

	// The height of the map in tiles.
	private int height;

	// 2D array of all the images that make up the backGround layer
	private String backgroundLayer;

	// 2D array of all the images that make up the collision layer
	private ArrayList<ArrayList<Integer>> collisionLayer;

	// 2D array of all the images that make up the environmental layer
	// Black(1) is for death
	// Green(2) is for mud
	// Blue(4) is for mist
	// Red(3) is for fire
	private ArrayList<ArrayList<Integer>> environmentalLayer;

	// The current player
	private Player currentPlayer;

	// The name of the map
	private String name;

	// Doors on the current map
	HashMap<DoorItem, Point> Doors;

	public Map(String name, Player player, List<Item> items, ArrayList<NPC> NPCS, HashMap<DoorItem, Point> doors)
			throws BadMapImageException, IOException {
		this.name = name;
		this.items = items;
		this.currentPlayer = player;
		this.backgroundLayer = name;
		this.NPCS = NPCS;
		this.Doors = doors;
		BufferedImage colLayer = this.loadImage(this.name, "Collision");
		this.collisionLayer = this.loadColLayers(colLayer);
		BufferedImage enviromentLayer = this.loadImage(this.name, "Environment");
		this.environmentalLayer = this.loadEnvLayers(enviromentLayer);
	}

	/**
	 * This method locates a map layer in the assets folder of the map class using a
	 * mapName and a layer
	 *
	 * @param mapName
	 * @param layer
	 * @return BufferedImage representing the layer of a given map
	 * @throws BadMapImageException
	 * @throws IOException
	 */
	private BufferedImage loadImage(String mapName, String layer) throws BadMapImageException, IOException {
		BufferedImage img = null;

		img = ImageIO.read(Map.class.getResource("assets/mapImages/" + mapName + layer + ".png"));
		if (img.getHeight() % 32 > 0 || img.getWidth() % 32 > 0) {
			throw new BadMapImageException(
					"The image you are trying to load does not have the correct Dimensions, Dimensions should be a factor of 32, the Global tile size.");
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
		this.width = colLayerUnbroken.getWidth() / 32;
		this.height = colLayerUnbroken.getHeight() / 32;
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
		for (int initY = 0; initY < heightUnbroken; initY += 32) {
			layer.add(new ArrayList<BufferedImage>());
		}

		for (int y = 0; y < heightUnbroken; y += 32) {
			for (int x = 0; x < widthUnbroken; x += 32) {
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
		if (Doors == null)
			return null;
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
		int middle = Map.tileSize / 2;
		if (items == null)
			return null;

		Item closest = null;
		for (Item item : this.items) {

		}
		return null;
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
		Point toDrop = new Point((int) x / Map.tileSize, (int) y / Map.tileSize);
		i.setX(x);
		i.setY(y);
	}

	/**
	 * This method removes a given item from the map by removing the item from the
	 * Maps HashMap of items.
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
		x = (int) (x / tileSize);
		y = (int) (y / tileSize);
		if (x < 0 || y < 0 || (x + this.width) > 0 || (y + this.height) > 0) {
			return false;
		}
		Point mapPos = this.positionOnMap(x, y);
		if (this.collisionLayer.get((int) mapPos.getY()).get((int) mapPos.getX()) == 1) {
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
	public boolean canMove(Rectangle r) {

		double topLx = r.getX();
		double topLy = r.getY();
		if (this.collisionLayer.get((int) (topLy / Map.tileSize)).get((int) (topLx / Map.tileSize)) == 1) {
			return false;
		}
		if (((int) (topLx / Map.tileSize)) < 0 || ((int) (topLy / Map.tileSize)) < 0) {
			return false;
		}

		double topRx = r.getX() + r.getWidth();
		double topRy = r.getY();
		if (this.collisionLayer.get((int) (topRy / Map.tileSize)).get((int) (topRx / Map.tileSize)) == 1) {
			return false;
		}
		if (((int) (topRx / Map.tileSize)) > 0 || ((int) (topRy / Map.tileSize)) > 0) {
			return false;
		}

		double botLx = r.getX();
		double botLy = r.getY() + r.getHeight();
		if (this.collisionLayer.get((int) (botLy / Map.tileSize)).get((int) (botLx / Map.tileSize)) == 1) {
			return false;
		}
		if (((int) (botLx / Map.tileSize)) < 0 || ((int) (botLy / Map.tileSize)) < 0) {
			return false;
		}

		double botRx = r.getX() + r.getWidth();
		double botRy = r.getY() + r.getHeight();
		if (this.collisionLayer.get((int) (botRy / Map.tileSize)).get((int) (botRx / Map.tileSize)) == 1) {
			return false;
		}
		if (((int) (botRx / Map.tileSize)) > 0 || ((int) (botRy / Map.tileSize)) > 0) {
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
	 * @return The environment on the tile at x,y
	 */
	public Environment onEnviromentTile(int x, int y) {
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
	 * This method returns whether there is a item on a given spot
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean itemAtTile(int x, int y) {
		Point pos = new Point((int) x / Map.tileSize, (int) y / Map.tileSize);
		for (Item itm : this.items) {
			if (itm.getX() == pos.getX() && itm.getY() == pos.getY()) {
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
	public Item itemAt(int x, int y) {
		Point pos = new Point((int) x / Map.tileSize, (int) y / Map.tileSize);
		for (Item itm : this.items) {
			if (itm.getX() == pos.getX() && itm.getY() == pos.getY()) {
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
	public List<NPC> getNPCS() {
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

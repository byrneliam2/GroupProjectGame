package player;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gfx.ImageLoader;
import gfx.ImageUtilities;
import map.Map;
import map.World;
import utils.MathUtils;
import items.Backpack;
import items.DoorItem;
import items.Equipable;
import items.InvalidBackpackException;
import items.Item;
import items.Usable;

/**
 * @author javahemohs Created by javahemohs on 19/09/17.
 *
 */
public class Player {
	/* constants */
	private static final int rangeCircleWidth = 2 * Map.tileSize;
	private final String name;
	private final double defaultFireRate = 1;


	private Item closestItem;
	private Backpack itemsList = new Backpack(this);
	private double fireRate = 1;
	private int maxHealth = 5;
	private int health = 5;
	private int speed = 8;
	private int xLocation;// centreX
	private int yLocation;// centreY
	private Map map;// the map which the player is currently located on.
	private boolean isAlive = true;
	private boolean isReadyToShoot = true;
	private Timer shootTimer = new Timer();
	private Image playerImage;

	private Ellipse2D.Double rangeCircle;// the range at which the player can 'pick up' items
	private Rectangle boundingBox;// the hit box of the player.

	/**
	 * @param name
	 * @param xLocation
	 * @param yLocation
	 */
	public Player(String name, int xLocation, int yLocation) {
		this.name = name;
		rangeCircle = new Ellipse2D.Double(xLocation, yLocation, rangeCircleWidth, rangeCircleWidth);
		boundingBox = new Rectangle(xLocation, yLocation, Map.tileSize, Map.tileSize);
	}

	/**
	 * This method sets the global tile size
	 * 	THIS IS A QUICK FIX, THIS NEEDS TO BE CHANGED
	 * @param newWidth
	 * @param newHeight
	 */
	public void setGlobalTileSize(int newWidth, int newHeight) {
			BufferedImage colLayer = ImageLoader.image("MapImages", "Map3Collision", true);
			int width = colLayer.getWidth() / 32;
			int height = colLayer.getHeight() / 32;

			colLayer = ImageUtilities.scale(colLayer, newWidth, newHeight);
			Map.tileSize = (int) newWidth / width;

	}

	/**
	 * Adds the closest item to the player ot the player's backpack. If item is a
	 * key, adds it to the key section of the backpack. Also tells the map to remove
	 * the item from the map.
	 *
	 * @param item
	 *            item to pickup.
	 * @throws InvalidPlayerExceptions
	 *             if the backpack is full, or the item already belongs to the
	 *             player or, there is no item next to the player
	 */
	public void pickUpItem() throws InvalidPlayerExceptions {
		try {
			// add the item to the current player pack.
			itemsList.pickUpItem(closestItem);
			// tells map item has been picked up, so map can remove it from map.
			map.removeItem(closestItem);
			// updates closest item to player
			closestItem = map.getClosestItem(rangeCircle);

		} catch (InvalidBackpackException e) {
			// If the Player is trying to pickup an item which doesn't exists return this
			// exception.
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}

	/**
	 * Remove an item from the BackPack placing it onto the map at the player's
	 * current location.
	 *
	 * @param item
	 *            item to remove from backpack
	 * @throws InvalidPlayerExceptions
	 *             if the backpack doesnt contain the item.
	 */
	public void removeItem(Item item) throws InvalidPlayerExceptions {
		try {
			// Remove the Item from the Player list of Items.
			itemsList.removeItem(item);
			// update the map with the item that has been dropped
			map.placeItem(item, (int) boundingBox.getCenterX(), (int) boundingBox.getCenterY());
			// update the Closest item to the player rangeCircle
			closestItem = map.getClosestItem(rangeCircle);

		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}

	/**
	 * Equips this item, providing its given bonuses to the player, moving the item
	 * into the 'equipped' section of the player's backpack.
	 *
	 * @param item
	 *            item to equip
	 * @throws InvalidPlayerExceptions
	 *             if the player already has the max number of items equipped or the
	 *             item is not part of a player's backpack.
	 */
	public void equipItem(Equipable item) throws InvalidPlayerExceptions {
		try {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			itemsList.equipItem(item);
		} catch (InvalidBackpackException e) {
			// If the Player is trying to equip an item which doesn't exists in the BackPack
			// return this exception.
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}

	/**
	 * Unequips this item, removing its given bonuses from the player it was
	 * equipped to and moving it out of the 'equipped' section of the backpack.
	 *
	 * @param item
	 * @throws InvalidPlayerExceptions
	 *             if the item was not equipped to any player or the pack's
	 *             unequipped area is full.
	 */
	public void unequipItem(Equipable item) throws InvalidPlayerExceptions {
		try {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			itemsList.unequipItem(item);
		} catch (InvalidBackpackException e) {
			// If the Player is trying to unequip an item which doesn't exists in the
			// BackPack return this exception.
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}

	/**
	 * Uses this item on the player and removes it from the inventory
	 *
	 * @param item
	 *            item to use
	 * @throws InvalidPlayerExceptions
	 *             if the item was not part of a player's backpack.
	 */
	public void useItem(Usable item) throws InvalidPlayerExceptions {
		try {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			itemsList.useItem(item);
		} catch (InvalidBackpackException e) {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			throw new InvalidPlayerExceptions(e.getMessage());

		}
	}

	/**
	 * pick Up And Use the item without putting it in the backpack.
	 *
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void pickUpAndUse(Usable item) throws InvalidPlayerExceptions {
		try {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			itemsList.pickUpAndUse(item);
		} catch (InvalidBackpackException e) {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			throw new InvalidPlayerExceptions(e.getMessage());

		}
	}

	public void takeDamage() {
		// Decrease the health of the player by one
		this.health -= 1;
		// check if the player is still alive and flip the IsAlive flag and then if the
		// player is dead we have to end the game.
		if (health == 0) {
			// Change the isAlive flag to dead.
			isAlive = false;
		} else {
			// Change the isAlive flag to still surviving.
			isAlive = true;
		}
	}

	/**
	 * If it is possible for a player to open a door.
	 *
	 * @param doorItem
	 * @return
	 */
	public boolean canOpenDoor(DoorItem doorItem) {
		// Check if the player has the Specific KeyId to the Specific DoorId then they
		// can go throw the door
		if (itemsList.checkDoorID(doorItem.getDoorID())) {
			return true;
		}
		return false;
	}

	/**
	 * @param dx
	 * @param dy
	 * @return true if the player moved through a door, false otherwise.
	 * @throws InvalidPlayerExceptions
	 *             if the player tries to make an invalid move.
	 */
	public boolean move(int dx, int dy) throws InvalidPlayerExceptions {
		DoorItem door = null;
		boundingBox.translate(dx, dy);
		if (map.canMove(boundingBox)) {

			if ((door = map.getDoor(boundingBox)) != null) {// if player is next to a door
				map = enterDoor(door);
				closestItem = map.getClosestItem(rangeCircle);
				return true;
			} else {// make a normal move.
				// update boundingBox and rangeCircle
				rangeCircle = new Ellipse2D.Double(boundingBox.getX(), boundingBox.getY(), rangeCircleWidth,
						rangeCircleWidth);
				// update closest item
				closestItem = map.getClosestItem(rangeCircle);
				return false;
			}
		} else {
			boundingBox.translate(-dx, -dy);
			throw new InvalidPlayerExceptions("You cant make a move/Invalid move");
		}
	}

	/**
	 * This method takes a door and returns the map that it leads too
	 *
	 * @param Door
	 * @return
	 */
	private Map enterDoor(DoorItem Door) {
		// update location in new map...
		boundingBox.setLocation(100, 100);
		rangeCircle = new Ellipse2D.Double(boundingBox.getX(), boundingBox.getY(), rangeCircleWidth, rangeCircleWidth);
		return World.maps.get(Door.getMap());
	}

	/**
	 * @param direction
	 *            should be an angle between 0 and 2Pi. (there's a method in
	 *            npc/NPC/getAngleToPlayer() which you can copy/use to calculate the
	 *            angle from player to mouse if needed).
	 * @throws InvalidPlayerExceptions
	 *             if your gun is not ready to be fired yet.
	 */
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		// check if able to shoot, if can't shoot yet, throw exception...

		if (isReadyToShoot) {
			isReadyToShoot = false;
			double direction = MathUtils.calculateAngle(boundingBox.getX(), boundingBox.getY(), mouseX, mouseY);
			// make a new bullet
			new Bullet(boundingBox.getX(), boundingBox.getY(), direction, this);

			// start a timer to count till when the next shot is ready to shoot....
			TimerTask taskEvent = new TimerTask() {
				public void run() {
					isReadyToShoot = true;
				}
			};
			shootTimer.schedule(taskEvent, (int) (fireRate * 1000));

		} else {// if you can't shoot (for any reason) throw an exception...
			throw new InvalidPlayerExceptions("You cant shoot at this stage");
		}

	}

	/*
	 * Returns the name
	 */
	public String getName() {
		return this.name;
	}

	public Backpack getItemsList() {
		return itemsList;
	}

	public void resetBackPack() {
		this.itemsList = new Backpack(this);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health > maxHealth)
			health = maxHealth;
		this.health = health;
	}

	/**
	 * @return the x pixel location of the player's centre point.
	 */
	public int getxLocation() {
		return (int) boundingBox.getX();
	}

	/**
	 * @return the y pixel location of the player's centre point.
	 */
	public int getyLocation() {
		return (int) boundingBox.getY();
	}

	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	public Map getMap() {
		return this.map;
	}

	public void setMap(Map m) {
		this.map = m;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public Backpack getBackpack() {
		return this.itemsList;
	}

	public void setMaxHealth(int max) {
		this.maxHealth = max;
		if (health > maxHealth)
			health = maxHealth;
	}

	public double getFireRate() {
		return fireRate;
	}

	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}

	public double getDefaultFireRate() {
		return defaultFireRate;
	}

	public Ellipse2D getRangeCircle() {
		return this.rangeCircle;
	}

	public Player getPlayer() {
		return this;
	}

	public Image getPlayerImage() {
		return playerImage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setPlayerImage(Image playerImage) {
		this.playerImage = playerImage;
	}

}

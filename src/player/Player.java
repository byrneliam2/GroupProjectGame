package player;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import map.Map;
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
	private static final int rangeCircleWidth = 50;
	private final String name;
	private final double defaultFireRate = 1;

	private Item closestItem;
	private Backpack itemsList = new Backpack(this);
	private double fireRate = 1;
	private int maxHealth = 5;
	private int health = 5;
	private int xLocation;
	private int yLocation;
	private Map map;// the map which the player is currently located on.

	private Ellipse2D.Double rangeCircle;// the range at which the player can 'pick up' items
	private Rectangle boundingBox;// the hit box of the player.

	/**
	 * @param name
	 * @param xLocation
	 * @param yLocation
	 */
	public Player(String name, int xLocation, int yLocation, Map startingMap) {
		this.name = name;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		rangeCircle = new Ellipse2D.Double(xLocation - (rangeCircleWidth / 2), yLocation - (rangeCircleWidth / 2),
				rangeCircleWidth, rangeCircleWidth);
		boundingBox = new Rectangle(xLocation - (Map.tileSize / 2), yLocation - (Map.tileSize / 2), Map.tileSize,
				Map.tileSize);
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

			if (closestItem == null)// throw exception....
				// otherwise...
				itemsList.pickUpItem(closestItem);
			// map.pickUpItem(closestItem);//tells map item has been picked up, so map can
			// remove it from map.
			// closestItem = map.closestItem();//updates closest item to player

		} catch (InvalidBackpackException e) {
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
			itemsList.removeItem(item);
			// map.placedItem(item);
			// closestItem = map.closestItem
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
			itemsList.equipItem(item);
		} catch (InvalidBackpackException e) {
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
			itemsList.unequipItem(item);
		} catch (InvalidBackpackException e) {
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
			itemsList.useItem(item);
		} catch (InvalidBackpackException e) {
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
			itemsList.pickUpAndUse(item);
		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());

		}
	}

	public void takeDamage() {
		// causes the player to take damage and lose health
		// should check whether the player is not dead....
		return;
	}

	/**
	 * If it is possible for a player to open a door.
	 *
	 * @param doorItem
	 * @return
	 */
	public boolean canOpenDoor(DoorItem doorItem) {
		if (itemsList.checkDoorID(doorItem.getDoorID())) {
			return true;
		}
		return false;
	}

	private boolean canMakeMove(int dx, int dy) {
		// Ask the map if it possible to move (((Later)))
		if (true) {
			return true;// if possible to move to this location
		}
		return false;// if not possible
	}

	/**
	 * @param dx
	 * @param dy
	 * @return true if the player moved through a door, false otherwise.
	 * @throws InvalidPlayerExceptions
	 */
	public boolean move(int dx, int dy) throws InvalidPlayerExceptions {
		// (Use the canMove() function from map class.)
		// Check if you can make the move and if we can, then do the following:

		// update the x and y
		// update closest item to player
		// closestItem = map.getClosestItem//....
		// check that you arnt touching a door (use getDoor() method in map), if you are
		// touching a door, move to next
		// map.

		// if you can't move, throw an exception...
		return false;
	}

	public void pauseGame() {
		// flip a global static variables, make sure all timerTasks do nothing while
		// pause is true and controller doesn't work (except for unpause button)while
		// pause is true
	}

	/**
	 * @param direction
	 *            should be an angle between 0 and 2Pi. (there's a method in
	 *            npc/NPC/getAngleToPlayer() which you can copy/use to calculate the
	 *            angle from player to mouse if needed).
	 * @throws InvalidPlayerExceptions
	 *             if your gun is not ready to be fired yet.
	 */
	public void shoot(double direction) throws InvalidPlayerExceptions {
		// make new bullet and add it to bullet list in the bullet class.
		// [extra] make it so that you can only shoot say once every second.

		// if you can't shoot (for any reason) throw an exception...
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
		this.health = health;
	}

	public int getxLocation() {
		return xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	public Map getMap() {
		return this.map;
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
}
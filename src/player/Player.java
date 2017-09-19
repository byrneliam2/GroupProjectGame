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
 * @author javahemohs
 *         Created by javahemohs on 19/09/17.
 *
 */
public class Player {
	/** constants */
	private static final int rangeCircleWidth = 50;
	private Item closestItem;
	private String name;
	private Backpack itemsList = new Backpack(this);
	private int health;
	private int xLocation;
	private int yLocation;
	private Map map;
	private Ellipse2D.Double rangeCircle = new Ellipse2D.Double(xLocation - (rangeCircleWidth / 2),
			yLocation - (rangeCircleWidth / 2), rangeCircleWidth, rangeCircleWidth);
	private Rectangle boundingBox = new Rectangle(xLocation - (Map.tileWidth / 2), yLocation - (Map.tileWidth / 2),
			Map.tileWidth, Map.tileHeight);

	/**
	 * @param name
	 * @param xLocation
	 * @param yLocation
	 */
	public Player(String name, int xLocation, int yLocation) {
		this.name = name;
		this.health = 5;
		this.xLocation = xLocation;
		this.yLocation = yLocation;

	}

	/**
	 * Pick up an item and put it into the list of BackPack
	 * 
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void pickUpItem() throws InvalidPlayerExceptions {
		try {

			if (closestItem == null)// throw exception....

				itemsList.pickUpItem(closestItem);
			// map.pickUpItem(closestItem);//tells map item has been picked up, so map can remove it from map.
			// closestItem = map.closestItem();//updates closest item to player

		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}

	/**
	 * Remove the item from the BackPack list
	 * 
	 * @param item
	 * @throws InvalidPlayerExceptions
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
	 * equip the item from the BackPack list
	 * 
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void equipItem(Equipable item) throws InvalidPlayerExceptions {
		try {
			itemsList.equipItem(item);
		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}

	/**
	 * unequip the item from the list
	 * 
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void unequipItem(Equipable item) throws InvalidPlayerExceptions {
		try {
			itemsList.unequipItem(item);
		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());

		}
	}

	/**
	 * check if there is an item in the BackPack List
	 * 
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void useItem(Usable item) throws InvalidPlayerExceptions {
		try {
			itemsList.useItem(item);
		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());

		}
	}

	/**
	 * pickUpAndUse the item without putting it in the backpack.
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

	public void move(int dx, int dy) throws InvalidPlayerExceptions {
		// (Use the canMove() function from map class.)
		// Check if you can make the move and if we can, then do the following:

		// update the x and y
		// update closest item to player
		// closestItem = map.getClosestItem//....

		// if you can't move, throw an exception...
	}

	/**
	 * @param direction
	 *            should be an angle between 0 and 2Pi. (there's a method in npc/NPC/getAngleToPlayer() which you can
	 *            copy/use to calculate the angle from player to mouse if needed).
	 */
	public void shoot(double direction) throws InvalidPlayerExceptions {
		// make new bullet and add it to bullet list in the bullet class.
		// [extra] make it so that you can only shoot say once every second.

		// if you can't shoot (for any reason) throw an exception...
	}

	/*
	 * Returns a String
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * Sets the name of
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Backpack getItemsList() {
		return itemsList;
	}

	public void setItemsList(Backpack itemsList) {
		this.itemsList = itemsList;
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

	public Map getMap() {
		return this.map;
	}

	public int getyLocation() {
		return yLocation;
	}

}
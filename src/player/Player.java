package player;

import java.awt.Rectangle;

import map.Map;
import items.Backpack;
import items.DoorItem;
import items.Equipable;
import items.InvalidBackpackException;
import items.Item;
import items.Usable;

/**
 * @author javahemohs
 * Created by javahemohs on 19/09/17.
 *
 */
public class Player {
	// need to implement the bounding box for the player when the player is making a move
	// the I have to change the bounding box of the player.
	private Rectangle boundingBox = new Rectangle (Map.tileWidth, Map.tileHeight);
	private String name;
	private Backpack itemsList = new Backpack(this);
	private int health;
	private int xLocation;
	private int yLocation;
	private Map map;

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
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void pickUpItems(Item item) throws InvalidPlayerExceptions {
		try {
			itemsList.pickUpItem(item);

		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());
		}
	}


	/**
	 * Remove the item from the BackPack list
	 * @param item
	 * @throws InvalidPlayerExceptions
	 */
	public void removeItems(Item item) throws InvalidPlayerExceptions {
		try {
			itemsList.removeItem(item);
		} catch (InvalidBackpackException e) {
			throw new InvalidPlayerExceptions(e.getMessage());
		}

	}


	/**
	 * equip the item from the BackPack list
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
	 * pickUpAndUse the item from the BackPack list
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

	public void checkHit() {
		// checks whether any bullets that don't belong to 'this' player have hit this
		// player if it has hit the player, it should call stop() on the bullet, delete
		// the bullet from the bullet list.and player should lose health
		return;
	}

	/**
	 * If it is possible for a player to open a door.
	 * @param doorItem
	 * @return
	 */
	public boolean canOpenDoor(DoorItem doorItem) {
		if (itemsList.checkDoorID(doorItem.getDoorID())) {
			return true;
		}
		return false;
	}

	private boolean canMakeMove() {
		// Ask the map if it possible to move (((Later)))
		if (true) {
			return true;
		}
		return false;
	}

	public void move() {
		// Check if you can make the move and then update the x and y.

	}

	public void shoot() {
		// make new bullet and add it to bullet list in the bullet class.
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
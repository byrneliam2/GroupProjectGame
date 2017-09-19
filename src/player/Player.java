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
 * Created by javahemohs on 19/09/17.
 *
 */
public class Player {
	/**constants*/
	private static final int rangeCircleWidth = 50;
	private Item closestItem;
	private String name;
	private Backpack itemsList = new Backpack(this);
	private int health;
	private int xLocation;
	private int yLocation;
	private Map map;
	private Ellipse2D.Double rangeCircle = new Ellipse2D.Double(xLocation - (rangeCircleWidth/2)  , yLocation - (rangeCircleWidth/2), rangeCircleWidth, rangeCircleWidth);
	private Rectangle boundingBox = new Rectangle (xLocation - (Map.tileWidth/2),yLocation-(Map.tileWidth/2), Map.tileWidth, Map.tileHeight);


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
	public void pickUpItems() throws InvalidPlayerExceptions {
		try {

			if(closestItem==null)//throw exception....

			itemsList.pickUpItem(closestItem);
			//map.pickUpItem(closestItem);//tells map item has been picked up, so map can remove it from map.
			//closestItem = map.closestItem();//updates closest item to player

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
			//map.placedItem(item);
			//closestItem = map.closestItem
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
		// Check if you can make the move and then update the x and y.+
		// When moving to the new Cell check if there is an item for keyboard listener
		// Can move function from the map

		//each time you move, you need to update closest item to player
		//closestItem = map.getClosestItem//....
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
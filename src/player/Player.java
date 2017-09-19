package player;

import java.util.ArrayList;
import java.util.List;

import JamesPackage.Map;
import items.Backpack;
import items.DoorItem;

/**
 * Created by javahemohs on 19/09/17.
 */
public class Player {
	private String name;
	private Backpack itemsList = new Backpack(this);
	private int health;
	private int xLocation;
	private int yLocation;
	private Map map;

	public Player(String name, int xLocation, int yLocation) {
		this.name = name;
		this.health = 5;
		this.xLocation = xLocation;
		this.yLocation = yLocation;

	}

	public void pickUpItems() {
		if (true) {

		}

	}

	public void canRemoveItems() {
	}

	public void canEquipItem() {
	}

	public void canUnequipItem() {
	}

	public void canUseItem() {
	}

	public void canPickUpAndUse() {
	}

	public void checkHit() {
		// checks whether any bullets that don't belong to 'this' player have hit this
		// player if it has hit the player, it should call stop() on the bullet, delete
		// the bullet from the bullet list.and player should lose health
		return;
	}

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
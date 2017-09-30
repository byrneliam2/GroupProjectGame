package player;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Timer;
import java.util.TimerTask;

import game.Game;
import items.Backpack;
import items.DoorItem;
import items.Equipable;
import items.InvalidBackpackException;
import items.Item;
import items.Usable;
import map.Map;
import map.World;
import utils.MathUtils;

/**
 * @author javahemohs Created by javahemohs on 19/09/17.
 *
 */
public class Player {
	/* constants */
	private static final int rangeCircleWidth = 2 * Map.tileSize;
	private static final double defaultFireRate = 1;

	private final String name;
	private Item closestItem;
	private Backpack itemsList = new Backpack(this);

	protected int health = 5;
	private int maxHealth = 5;
	private int speed = 6;
	private int xLocation;// centreX
	private int yLocation;// centreY
	private double fireRate = 1;// in seconds, smaller numbers mean less time between shots
	private static Timer shotTimer = new Timer();
	private Map map;// the map which the player is currently located on.
	private boolean isDead = false;
	private boolean isReadyToShoot = true;

	private Ellipse2D.Double rangeCircle;// the range at which the player can 'pick up' items
	private Rectangle playerBox;// the hit box representing the location of the player.

	/**
	 * @param name
	 * @param xLocation
	 * @param yLocation
	 */
	public Player(String name, int xLocation, int yLocation) {
		this.name = name;
		rangeCircle = new Ellipse2D.Double(xLocation - Map.tileSize / 2, yLocation - Map.tileSize / 2, rangeCircleWidth,
				rangeCircleWidth);
		playerBox = new Rectangle(xLocation + 3, yLocation + 3, Map.tileSize - 6, Map.tileSize - 6);
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
		if (Game.GAME_PAUSED) {
			throw new InvalidPlayerExceptions("Game is paused, you cannot pickup Items");
		}
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
			map.placeItem(item, (int) playerBox.getCenterX(), (int) playerBox.getCenterY());
			// update the Closest item to the player rangeCircle
			closestItem = item;

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
			// equips the item
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
			// un-equips the item
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
			// use the item
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
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			itemsList.pickUpAndUse(item);
		} catch (InvalidBackpackException e) {
			// If the player wants to use the supply with the necessary items for a
			// particular purpose.
			throw new InvalidPlayerExceptions(e.getMessage());

		}
	}

	/**
	 * Causes the player to take 1 damage and if the player is dead, changes the isDead flag to true.
	 */
	public void takeDamage() {
		// Decrease the health of the player by one
		this.health -= 1;
		// check if the player is still alive and flip the IsAlive flag
		if (health <= 0) {
			isDead = true;
		}
	}

	/**
	 * If it is possible for a player to open a door.
	 *
	 * @param doorItem
	 * @return true if the player can open the door
	 */
	public boolean canOpenDoor(DoorItem doorItem) {
		// Check if the player has the Specific KeyId to the Specific DoorId
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
		if (Game.GAME_PAUSED) {
			throw new InvalidPlayerExceptions("Game is paused, you cannot move");
		}

		// temporary solution to stopping diagonal movements from being twice as fast.
		if (Math.abs(dy) == Math.abs(dx) && dy != 0) {
			dx = (int) Math.round(dx * 0.5);
			dy = (int) Math.round(dy * 0.5);
		}

		playerBox.translate(dx, dy);
		if (map.canMove(playerBox)) {
			// if player is next to a door and the door is unlocked or you have the key, go through...
			if ((door = map.getDoor(playerBox)) != null && (!door.isLocked() || canOpenDoor(door))) {
				map = enterDoor(door);
				closestItem = map.getClosestItem(rangeCircle);
				return true;
			} else {// make a normal move.
				// update rangeCircle
				rangeCircle.setFrame(rangeCircle.getX() + dx, rangeCircle.getY() + dy, rangeCircleWidth,
						rangeCircleWidth);
				// update closest item
				closestItem = map.getClosestItem(rangeCircle);
				return false;
			}
		} else {
			playerBox.translate(-dx, -dy);
			throw new InvalidPlayerExceptions("You cant make a move/Invalid move");
		}
	}

	/**
	 * This method closes the current map and starts the new map, returns the new map that the door leads to.
	 *
	 * @param Door
	 * @return
	 */
	private Map enterDoor(DoorItem Door) {
		// update location in new map...
		playerBox.setLocation(1500, 100);

		rangeCircle = new Ellipse2D.Double(xLocation - Map.tileSize / 2, yLocation - Map.tileSize / 2, rangeCircleWidth,
				rangeCircleWidth);
		map.pauseMapNPCs();
		Map newMap = World.maps.get(Door.getMap());
		newMap.startMapNPCs();
		return newMap;
	}

	/**
	 * @param direction
	 *            should be an angle between 0 and 2Pi. (there's a method in
	 *            MathUtil package. which you can use to calculate the
	 *            angle from player to mouse if needed).
	 * @throws InvalidPlayerExceptions
	 *             if your gun is not ready to be fired yet.
	 */
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		if (Game.GAME_PAUSED) {
			throw new InvalidPlayerExceptions("Game is paused, you cannot shoot");
		}

		if (isReadyToShoot) {
			isReadyToShoot = false;
			double direction = MathUtils.calculateAngle(playerBox.getX(), playerBox.getY(), mouseX, mouseY);
			// make a new bullet
			new Bullet(getCentreX(), getCentreY(), direction, this, 8);

			// start a timer to count till when the next shot is ready to shoot....
			TimerTask taskEvent = new TimerTask() {
				public void run() {
					isReadyToShoot = true;
				}
			};
			shotTimer.schedule(taskEvent, (int) (fireRate * 1000));

		} else {// if you can't shoot (for any reason) throw an exception...
			throw new InvalidPlayerExceptions("You cant shoot because your weapon is not ready to shoot yet....");
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

	/**
	 * @param health
	 *            the health to set health to, can't be more than max health...
	 */
	public void setHealth(int health) {
		if (health > maxHealth)
			health = maxHealth;
		this.health = health;
	}

	/**
	 * @return the x pixel location of the player's top left point.
	 */
	public int getxLocation() {
		return (int) playerBox.getX() - 3;
	}

	/**
	 * @return the y pixel location of the player's top left point.
	 */
	public int getyLocation() {
		return (int) playerBox.getY() - 3;
	}

	public int getCentreX() {
		return (int) playerBox.getCenterX();
	}

	public int getCentreY() {
		return (int) playerBox.getCenterY();
	}

	public Rectangle getBoundingBox() {
		return this.playerBox;
	}

	public Map getMap() {
		return this.map;
	}

	public boolean isDead() {
		return this.isDead;
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}

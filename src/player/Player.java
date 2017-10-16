package player;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import common.player.IPlayer;
import game.Game;
import items.Backpack;
import items.DoorItem;
import items.Equipable;
import items.InvalidBackpackException;
import common.items.Item;
import items.Usable;
import map.Environment;
import map.Map;
import map.World;
import common.utils.Direction;
import common.utils.MathUtils;

/**
 * @author javahemohs and Thomas Edwards Created by javahemohs on 19/09/17.
 *
 */
public class Player implements IPlayer, Serializable {
	/* constants */
	private static final int rangeCircleWidth = 2 * Map.tileSize;
	private static final double defaultFireRate = 0.8;
	private static final int baseSpeed = 6;

	// timer for standing in fire...
	private javax.swing.Timer fireTimer = new javax.swing.Timer(1000, (e) -> takeDamage());
	private final String name;
	private Item closestItem;
	private Backpack itemsList = new Backpack(this);
	protected boolean isDead = false;
	protected int health = 5, maxHealth = 5;
	protected int speed = baseSpeed;// movement speed of the player
	private double fireRate = defaultFireRate;// in seconds, smaller numbers mean less time between shots
	private static Timer shotTimer = new Timer();
	protected Map map;// the map which the player is currently located on.
	private boolean isReadyToShoot = true;
	private boolean isSpecialReady = true;
	private Environment currentEnvironment;
	private Direction currentDir = Direction.S;
	private boolean isMoving = false;

	private Ellipse2D.Double rangeCircle;// the range at which the player can 'pick up' items
	protected Rectangle.Double playerBox;// the hit box representing the location of the player.

	/**
	 * @param name
	 * @param xLocation
	 * @param yLocation
	 */
	public Player(String name, int xLocation, int yLocation) {
		this.name = name;
		rangeCircle = new Ellipse2D.Double(xLocation - Map.tileSize / 2, yLocation - Map.tileSize / 2, rangeCircleWidth,
				rangeCircleWidth);
		playerBox = new Rectangle.Double(xLocation + 4, yLocation + 4, Map.tileSize - 8, Map.tileSize - 8);
		fireTimer.setInitialDelay(500);// 0.5 seconds delay before first fire tick
	}

	/**
	 * Adds the closest item to the player ot the player's backpack. If item is a
	 * key, adds it to the key section of the backpack. Also tells the map to remove
	 * the item from the map. Also as of ~October causes the item to be immediatly
	 * equiped or used on pickup.
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
	 * Causes the player to take 1 damage and if the player is dead, changes the
	 * isDead flag to true.
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
	 *             if the player tries to make an invalid move. eg move into a wall.
	 */
	public boolean move(double dx, double dy) throws InvalidPlayerExceptions {
		slowPlayer();
		dx = dx * speed;
		dy = dy * speed;
		isMoving = true;
		// move the player's frame
		playerBox.setFrame(playerBox.getX() + dx, playerBox.getY() + dy, playerBox.getWidth(), playerBox.getHeight());
		if (map.canMove(playerBox)) {
			DoorItem door = null;
			// if player is next to a door and the door is unlocked or you have the key, go
			// through...
			if ((door = map.getDoor(playerBox)) != null && (!door.isLocked() || canOpenDoor(door))) {
				map = enterDoor(door);
				closestItem = map.getClosestItem(rangeCircle);
				return true;
			} else {// make a normal move.
				// update rangeCircle
				rangeCircle.setFrame(rangeCircle.getX() + dx, rangeCircle.getY() + dy, rangeCircleWidth,
						rangeCircleWidth);

				// update closest itemest item to player
				closestItem = map.getClosestItem(rangeCircle);

				updateEnvironment();

				return false;
			}
		} else {// player made an invalid move, so move player back to where he came from and
				// throw exception.
			playerBox.setFrame(playerBox.getX() - dx, playerBox.getY() - dy, playerBox.getWidth(),
					playerBox.getHeight());
			throw new InvalidPlayerExceptions("You cant make a move/Invalid move");
		}
	}

	private void updateEnvironment() {
		unSlowPlayer();
		// update the current Environment
		currentEnvironment = map.onEnvironmentTile((int) playerBox.getCenterX(), (int) playerBox.getCenterY());
		doFireEffect();
		doDeathEffect();
	}

	private void doDeathEffect() {
		if (currentEnvironment == Environment.DEATH) {
			isDead = true;
		}
	}

	private void doFireEffect() {
		if (currentEnvironment == Environment.FIRE) {
			if (fireTimer == null) {
				fireTimer = new javax.swing.Timer(1000, (e) -> takeDamage());
				fireTimer.start();
			}
		} else {
			if (fireTimer != null) {
				fireTimer.stop();
				fireTimer = null;
			}
		}
	}

	private void slowPlayer() {
		if (currentEnvironment == Environment.MUD)
			speed = baseSpeed / 3;
	}

	private void unSlowPlayer() {
		if (currentEnvironment == Environment.MUD)
			speed = baseSpeed;
	}

	/**
	 * This method closes the current map and starts the new map, returns the new
	 * map that the door leads to.
	 *
	 * @param Door
	 * @return
	 */
	private Map enterDoor(DoorItem Door) {
		Map newMap = map.getWorld().getMaps().get(Door.getMap());
		// works because each doorWay has two door items of the same name.
		DoorItem oppDoor = newMap.getDoor(Door.getDoorID());

		// update player's location on new map...
		setPlayerPosition(oppDoor);

		rangeCircle = new Ellipse2D.Double(playerBox.getX() - Map.tileSize / 2, playerBox.getY() - Map.tileSize / 2,
				rangeCircleWidth, rangeCircleWidth);

		map.pauseMapNPCs();
		// stops and removes all the bullets from the game when you go through the door.
		for (int i = Bullet.bulletList.size() - 1; i >= 0; i--) {
			Bullet.bulletList.get(i).removeBullet();
		}
		// start the new map off...
		newMap.startMapNPCs();
		return newMap;
	}

	/**
	 * Sets the player's position in the new map next to the door he just came
	 * through.
	 *
	 * @param door
	 *            the door to set the player's position at
	 */
	private void setPlayerPosition(DoorItem door) {
		if (door.getX() == 1860) {// entering on right...
			playerBox.setFrame(1820, door.getY(), playerBox.width, playerBox.height);
		} else if (door.getX() == 0) {// entering on left
			playerBox.setFrame(40, door.getY(), playerBox.width, playerBox.height);
		} else if (door.getY() == 1020) {// entering on bottom
			playerBox.setFrame(door.getX(), 980, playerBox.width, playerBox.height);
		} else if (door.getY() == 0) {// entering on bottom
			playerBox.setFrame(door.getX(), 40, playerBox.width, playerBox.height);
		}
	}

	/**
	 * Shoots a bullet from the player's current coordinates to the coordinates
	 * given by the mouse
	 *
	 * @param direction
	 *            should be an angle between 0 and 2Pi. (there's a method in
	 *            MathUtil package. which you can use to calculate the angle from
	 *            player to mouse if needed).
	 * @throws InvalidPlayerExceptions
	 *             if your gun is not ready to be fired yet.
	 */
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {

		if (isReadyToShoot) {// can only shoot if your gun is ready.
			isReadyToShoot = false;

			double direction = MathUtils.calculateAngle(getCentreX(), getCentreY(), mouseX, mouseY);
			// make a new bullet
			new Bullet(getCentreX(), getCentreY(), direction, this, 9, "playerBullet1");

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
	 * Atm this is a shotgun blast that shoots 3 bullets instead of the normal 1
	 * bullet. has an original 5 second cooldown.
	 */
	public void specialAbility(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		if (isSpecialReady) {// can only special if special is ready
			isSpecialReady = false;

			double direction = MathUtils.calculateAngle(getCentreX(), getCentreY(), mouseX, mouseY);
			// make new bullets
			new Bullet(getCentreX(), getCentreY(), direction, this, 9, "playerBullet1");
			new Bullet(getCentreX(), getCentreY(), direction - Math.PI / 16, this, 9, "playerBullet1");
			new Bullet(getCentreX(), getCentreY(), direction + Math.PI / 16, this, 9, "playerBullet1");

			// start a timer to count till when the next shot is ready to shoot....
			TimerTask taskEvent = new TimerTask() {
				public void run() {
					isSpecialReady = true;
				}
			};
			shotTimer.schedule(taskEvent, (int) (fireRate * 5000));

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
	 *            the health to set health to, ensures that it isn't more than max
	 *            health.
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
		return (int) Math.round(playerBox.getX() - 3);
	}

	/**
	 * @return the y pixel location of the player's top left point.
	 */
	public int getyLocation() {
		return (int) Math.round(playerBox.getY() - 3);
	}

	public int getCentreX() {
		return (int) Math.round(playerBox.getCenterX());
	}

	public int getCentreY() {
		return (int) Math.round(playerBox.getCenterY());
	}

	public Rectangle.Double getBoundingBox() {
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

	/**
	 * Sets the max health, ensures that the player's current health is <= max
	 * health.
	 *
	 * @param max
	 */
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

	public Direction getCurrentDir() {
		return this.currentDir;
	}

	public void setCurrentDir(Direction newDir) {
		this.currentDir = newDir;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	protected void setBoundingBoxWidth(int width, int height) {
		this.playerBox.setFrame(playerBox.x, playerBox.y, width, height);
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

}

package items;

import java.awt.Point;
import java.io.Serializable;
import java.awt.Rectangle;

import common.items.Item;
import map.Map;

/**
 * This class represent a door, a door leads to another map. In this case each
 * door has the name of the map it leads to.
 *
 * @author James and Thomas
 *
 */
public class DoorItem extends AbstractItem implements Serializable {

	private int doorID;
	private boolean locked;
	private Rectangle enterBox;// this is the collision rectangle, when the player collides with this rect, it
								// causes the play to move through the door

	public DoorItem(String map, int ID, boolean locked, int x, int y) {
		super(map, "A door linking to map: " + map + " with ID: " + ID, null);
		this.doorID = ID;
		this.locked = !locked;
		super.setX(x);
		super.setY(y);
		int size = 3;// size of the collision box to enter doors.
		enterBox = new Rectangle(x + Map.tileSize / 2 - size, y + Map.tileSize / 2 - size, size * 2, size * 2);
	}

	public Rectangle getEnterBox() {
		return enterBox;
	}

	public String getMap() {
		return super.name;
	}

	/**
	 * @return the ID of this door.
	 */
	public int getDoorID() {
		return doorID;
	}

	/**
	 * Unlocks the door.
	 */
	public void unlockDoor() {
		this.locked = false;
	}

	/**
	 * @return true if the door is locked, false otherwise.
	 */
	public boolean isLocked() {
		return locked;
	}

	@Override
	public int hashCode() {
		return doorID;
	}

	public Item clone() {
		return new DoorItem(this.name, this.doorID, this.locked, this.x, this.y);
	}

}

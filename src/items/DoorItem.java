package items;

import java.awt.Point;

import common.items.Item;
import map.Map;

/**
 * This class represent a door, a door leads to another map. In this case each
 * door has the name of the map it leads to.
 *
 * @author James
 *
 */
public class DoorItem extends AbstractItem {

	private int doorID;
	private boolean locked;
	private Point p;

	public DoorItem(String map, int ID, boolean locked, int x, int y) {
		super(map, "A door linking to map: " + map + " with ID: " + ID, null);
		this.doorID = ID;
		// this.locked = locked; FIXME
		super.setX(x);
		super.setY(y);
		p = new Point(x + Map.tileSize / 2, y + Map.tileSize / 2);
	}

	public Point getCentrePoint() {
		return p;
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

package items;

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

	public DoorItem(String map, int ID, boolean locked) {
		super(map, "A door linking to map: " + map + " with ID: " + ID, null);
		this.doorID = ID;
	}

	public String getMap() {
		return super.name;
	}

	public int getDoorID() {
		return doorID;
	}

	public void unlockDoor() {
		this.locked = false;
	}

	public boolean isLocked() {
		return locked;
	}

}

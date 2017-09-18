package items;

import java.awt.Image;

/**
 * This class represent a door, a door leads to another map. In this case each
 * door has the name of the map it leads to.
 *
 * @author James
 *
 */
public class DoorItem implements Item {

	private int doorID;
	private String map;

	public DoorItem(String map, int ID) {
		this.map = map;
		this.doorID = ID;
	}

	public String getMap() {
		return map;
	}

	public int getDoorID() {
		return doorID;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.map;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Backpack getPack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPack(Backpack pack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pickupItem(Backpack pack) throws InvalidBackpackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropItem(Backpack pack) throws InvalidBackpackException {
		// TODO Auto-generated method stub

	}

}

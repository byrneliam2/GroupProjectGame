package JamesPackage;

import java.awt.Image;

import items.Backpack;
import items.InvalidBackpackException;
import items.Item;

/**
 * This class represent a door, a door leads to another map. In this case each
 * door has the name of the map it leads to.
 * 
 * @author James
 *
 */
public class DoorItem implements Item {

	private String map;

	public DoorItem(String map) {
		this.map = map;
	}

	public String getMap() {
		return map;
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

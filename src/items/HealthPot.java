package items;

import java.awt.Image;

public class HealthPot implements Usable {

	private Image img;
	private String name, description;
	private Backpack pack;// is null if the item is not picked up

	public HealthPot() {
		this.name = "Health Pot";
		this.description = "A potion of health which immedietly restores x amount of health to the player";
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pickupItem(Backpack pack) throws InvalidBackpackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropItem(Backpack pack) throws InvalidBackpackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void use() throws InvalidBackpackException {
		// TODO Auto-generated method stub

	}

}

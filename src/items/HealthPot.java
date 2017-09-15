package items;

import java.awt.Image;

public class HealthPot implements Usable {

	private Image img;
	private String name, description;
	private Player owner;

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
	public void pickupItem(Player p) throws InvalidActionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void use(Player player) throws InvalidActionException {
		// TODO Auto-generated method stub

	}

}

package items.itemList;

import MohsenPackage.Player;
import items.AbstractItem;
import items.InvalidBackpackException;
import items.Usable;

/**
 * Restores a portion of health to the player
 * 
 * @author Thomas Edwards
 *
 */
public class HealthPot extends AbstractItem implements Usable {

	public HealthPot() {
		super("Health Pot", "A potion of health which immedietly restores x amount of health to the player", null);
	}

	@Override
	public void use(Player player) throws InvalidBackpackException {
		// TODO
	}

}

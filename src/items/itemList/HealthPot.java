package items.itemList;

import items.AbstractItem;
import items.Usable;
import player.Player;

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
	public void use(Player player) {
		// TODO
	}

}

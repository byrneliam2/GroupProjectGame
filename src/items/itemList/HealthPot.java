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
		super("Health Pot", "A potion of health which immedietly restores 1 health to the player", "healthPotion");
	}

	@Override
	public void use(Player player) {
		if (player.getHealth() > player.getMaxHealth() - 1)
			player.setHealth(player.getMaxHealth());
		else
			player.setHealth(player.getHealth() + 1);
	}

}

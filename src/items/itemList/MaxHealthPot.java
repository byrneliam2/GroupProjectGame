package items.itemList;

import items.AbstractItem;
import items.Usable;
import player.Player;

public class MaxHealthPot extends AbstractItem implements Usable {

	public MaxHealthPot() {
		super("Max Health Pot", "Restores the player to full health.", "maxHealthPot");
	}

	@Override
	public void use(Player player) {
		player.setHealth(player.getMaxHealth());
	}

}

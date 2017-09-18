package items.itemList;

import items.AbstractItem;
import items.Equipable;
import player.Player;

public class RustyArmour extends AbstractItem implements Equipable {

	public RustyArmour() {
		super("Rusty Armour", "Worn and beaten, this armour provides little protection", null);
	}

	@Override
	public void provideBonus(Player p) {
		// TODO
	}

	@Override
	public void removeBonus(Player p) {
		// TODO
	}

}

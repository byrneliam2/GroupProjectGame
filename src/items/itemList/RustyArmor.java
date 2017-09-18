package items.itemList;

import items.AbstractItem;
import items.Equipable;
import player.Player;

public class RustyArmor extends AbstractItem implements Equipable {

	public RustyArmor() {
		super("Rusty Armour", "Worn and beaten, this armour provides little protection", "armor1.png");
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

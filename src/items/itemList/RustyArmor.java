package items.itemList;

import items.AbstractItem;
import items.Equipable;
import player.Player;

public class RustyArmor extends AbstractItem implements Equipable {

	public RustyArmor() {
		super("Rusty Armour", "Worn and beaten, this armour provides +1 health", "rustyArmor");
	}

	@Override
	public void provideBonus(Player p) {
		p.setMaxHealth(p.getMaxHealth() + 1);
	}

	@Override
	public void removeBonus(Player p) {
		p.setMaxHealth(p.getMaxHealth() - 1);
	}

}

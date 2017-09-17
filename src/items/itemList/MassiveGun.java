package items.itemList;

import MohsenPackage.Player;
import items.AbstractItem;
import items.Equipable;

public class MassiveGun extends AbstractItem implements Equipable {

	public MassiveGun() {
		super("Massive Gun", "An incredibly large gun that packs a huge amount of firepower", null);
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

package items.itemList;

import items.AbstractItem;
import items.Equipable;
import player.Player;

public class MassiveGun extends AbstractItem implements Equipable {

	public MassiveGun() {
		super("Massive Gun",
				"An incredibly large gun that plows through monsters, sets fire rate to two shots per second",
				"massiveGun.png");
	}

	@Override
	public void provideBonus(Player p) {
		p.setFireRate(0.5);
	}

	@Override
	public void removeBonus(Player p) {
		p.setFireRate(p.getDefaultFireRate());
	}

}

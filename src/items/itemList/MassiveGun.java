package items.itemList;

import items.AbstractItem;
import items.Equipable;
import player.Player;

/**
 * @author Thomas Edwards
 */
public class MassiveGun extends AbstractItem implements Equipable {

	public MassiveGun() {
		super("Massive Gun", "An incredibly large gun that plows through monsters, doubles your fire rate",
				"massiveGun");
	}

	@Override
	public void provideBonus(Player p) {
		p.setFireRate(p.getFireRate() * 0.5);
	}

	@Override
	public void removeBonus(Player p) {
		p.setFireRate(p.getFireRate() / 0.5);
	}

}

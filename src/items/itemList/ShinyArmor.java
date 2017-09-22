package items.itemList;

import items.AbstractItem;
import items.Equipable;
import player.Player;

public class ShinyArmor extends AbstractItem implements Equipable {

	public ShinyArmor() {
		super("Shiny Armour", "Strong and shining, this armour provides +3 max health", "shinyArmor.png");
	}

	@Override
	public void provideBonus(Player p) {
		p.setMaxHealth(p.getMaxHealth() + 3);
	}

	@Override
	public void removeBonus(Player p) {
		p.setMaxHealth(p.getMaxHealth() - 3);
	}

}
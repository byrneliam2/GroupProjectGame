package items;

import common.items.Item;
import player.Player;

/**
 * Equipable items are items which can be 'equipped' by the player, they provide
 * static bonuses when eqquiped.
 *
 * @author Thomas Edwards
 *
 */
public interface Equipable extends Item {

	/**
	 * Provides item's given bonuses to the player,
	 * 
	 */
	void provideBonus(Player p);

	/**
	 * removes itemss given bonuses from the player it was
	 * equipped to.
	 *
	 */
	void removeBonus(Player p);

}

package items;

/**
 * Equipable items are items which can be 'equipped' by the player, they provide
 * static bonuses when eqquiped.
 *
 * @author edwardthom
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

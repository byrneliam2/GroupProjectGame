package items;

/**
 * Usable items are items which can be used (at any time) by the player and have
 * some kind of immediate effect. Usable items should ALWAYS be destroyed once they have been
 * used.
 *
 * @author edwardthom
 *
 */
public interface Usable extends Item {

	/**
	 * Uses this item on the player providing it's benefits to the player
	 */
	public void use(Player player) throws InvalidBackpackException;

}

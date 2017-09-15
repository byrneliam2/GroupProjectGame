package items;

/**
 * Usable items are items which can be used (at any time) by the player and have
 * some kind of immediate effect. Usable items are destroyed once they have been
 * used.
 *
 * @author edwardthom
 *
 */
public interface Usable extends Item {

	/**
	 * Uses this item on the player, then removes this item from the player's
	 * backpack.
	 *
	 * @param player
	 *            the player to use the item on
	 * @throws InvalidBackpackException
	 *             if the item was not part of a player's backpack.
	 */
	public void use() throws InvalidBackpackException;

}

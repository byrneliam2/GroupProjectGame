package items;

public interface Usable extends Item {

	/**
	 * Uses this item on the player, then removes this item from the player's
	 * backpack.
	 *
	 * @param player
	 *            the player to use the item on
	 * @throws InvalidActionException
	 *             if the item was not part of a player's backpack.
	 */
	public void use(Player player) throws InvalidActionException;

}

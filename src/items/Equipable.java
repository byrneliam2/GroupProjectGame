package items;

/**
 * Equipable items are items which can be 'equipped' by the player, they should
 * provide static bonuses when equiped.
 *
 * @author edwardthom
 *
 */
public interface Equipable extends Item {

	/**
	 * @param player
	 *            equips this item, providing its given bonuses to the specified
	 *            player, moving the item into the 'equipped section of the player's
	 *            backpack.
	 * @throws InvalidActionException
	 *             if the player already has the max number of items equipped or the
	 *             item is not part of a player's backpack.
	 */
	public void equip() throws InvalidActionException;

	/**
	 * Unequips this item, removing its given bonuses from the player it was
	 * equipped to.
	 *
	 * @throws InvalidActionException
	 *             if the item was not equipped to any player.
	 *
	 */
	public void unequip() throws InvalidActionException;

}

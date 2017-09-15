package items;

import java.awt.Image;

/**
 * Abstract Item class, all items of any type should implement these methods.
 *
 * @author edwardthom
 *
 */
public abstract interface Item {

	/**
	 * @return an image of this item.
	 */
	public Image getImage();

	/**
	 * @return the name of this item.
	 */
	public String getName();

	/**
	 * @return a description of this item including what benefits it provides when
	 *         used/equipped.
	 */
	public String getDescription();

	/**
	 * Moves the item into the player's backpack.
	 *
	 * @param pack
	 *            the backpack of the player who is picking up the item.
	 * @throws InvalidBackpackException
	 *             if the backpack is full or the item already belongs to the
	 *             player.
	 */
	public void pickupItem(Backpack pack) throws InvalidBackpackException;

	/**
	 * Removes the item from the player's backpack.
	 *
	 * @param pack
	 *            the backpack of the player who is dropping the item.
	 * @throws InvalidBackpackException
	 *             if the backpack doesn't contain this item.
	 */
	public void dropItem(Backpack pack) throws InvalidBackpackException;
}

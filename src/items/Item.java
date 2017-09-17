package items;

import java.awt.Image;

/**
 * Abstract Item interface, all items of any type should implement these
 * methods.
 *
 * @author edwardthom
 *
 */
public interface Item {

	/**
	 * @return returns an image of this item. No guareentees on the size of image returned.
	 */
	public Image getImage();

	/**
	 * @return returns the name of this item.
	 */
	public String getName();

	/**
	 * @return a description of this item including what benefits it provides when
	 *         used/equipped.
	 */
	public String getDescription();

	/**
	 * @return null if this item has no owner (hasn't been picked up), otherwise the backpack of the player who owns
	 *         this item.
	 */
	public Backpack getPack();

	/**
	 * Sets the pack (and thus the owner) of this item to the given pack.
	 * 
	 * @param pack
	 *            the pack to set this item to
	 */
	public void setPack(Backpack pack);

	/**
	 * Moves the item into the given player's backpack.
	 *
	 * @param pack
	 *            the backpack of the player who is picking up the item.
	 * @throws InvalidBackpackException
	 *             if the backpack is full or the item already belongs to the
	 *             player.
	 */
	public void pickupItem(Backpack pack) throws InvalidBackpackException;

	/**
	 * Removes the item from the given player's backpack.
	 *
	 * @param pack
	 *            the backpack of the player who is dropping the item.
	 * @throws InvalidBackpackException
	 *             if the backpack doesn't contain this item.
	 */
	public void dropItem(Backpack pack) throws InvalidBackpackException;
}

package items;

import java.awt.Image;

/**
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
	 * @param p
	 *            the player who is picking up the item
	 * @throws InvalidActionException
	 *             if the backpack is full.
	 */
	public void pickupItem(Player p) throws InvalidActionException;
}

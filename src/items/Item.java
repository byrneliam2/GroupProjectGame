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
	 * @return returns an image of this item. No guarantees on the size of image
	 *         returned. Is guaranteed that the image has a transparent background
	 *         so can be drawn on top of other images.
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
	 * @return null if this item has no owner (hasn't been picked up), otherwise the
	 *         backpack of the player who owns this item.
	 */
	public Backpack getPack();

	/**
	 * Sets the item's link to the given backpack
	 *
	 * @param pack
	 *            the backpack of the player who is picking up the item.
	 */
	public void pickUp(Backpack pack);

	/**
	 * Removes the link that this item has to the player's backpack.
	 *
	 */
	public void remove();

	/**
	 * @return the file path, atm it is just "ItemPictures/*itemName*"
	 */
	public String getFilePath();
}

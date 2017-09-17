package items;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Thomas Edwards
 *
 */
public class Backpack {
	/* Constants */
	public static final int MAX_EQUIPABLE_ITEMS = 2;
	public static final int MAX_PACK_ITEMS = 20;

	private Player owner;
	private List<Item> packItems = new ArrayList<Item>(MAX_PACK_ITEMS);
	private List<Equipable> equippedItems = new ArrayList<Equipable>(MAX_EQUIPABLE_ITEMS);

	public BackPack(Player owner){
		this.owner = owner;
	}

	/**
	 * Moves the item into the player's backpack.
	 *
	 * @param item
	 *            the item to pick up
	 * @throws InvalidBackpackException
	 *             if the backpack is full or the item already belongs to the
	 *             player.
	 */
	public void pickupItem(Item item) throws InvalidBackpackException {
		if (packItems.size() >= MAX_PACK_ITEMS)
			throw new InvalidBackpackException("BackPack is full, can't add anymore items to it");
		if (item.getPack() != null)
			throw new InvalidBackpackException("Item has already been picked up");

		packItems.add(item);
		item.setPack(this);
	}

	/**
	 * Removes the item from the player's backpack.
	 *
	 * @param item
	 *            the item to drop
	 * @throws InvalidBackpackException
	 *             if the backpack doesn't contain this item.
	 */
	public void dropItem(Item item) throws InvalidBackpackException {
		if (item.getPack() == null)
			throw new InvalidBackpackException("Item has not been picked up");
		if (!packItems.remove(item))
			throw new InvalidBackpackException(
					"Item was not in the backpack (Note. Item could be equipped in which case, unequip it, then drop it)");

		item.setPack(null);
	}

	/**
	 * Equips this item, providing its given bonuses to the player,
	 * moving the item into the 'equipped' section of the player's
	 * backpack.
	 * 
	 * @throws InvalidBackpackException
	 *             if the player already has the max number of items equipped or the
	 *             item is not part of a player's backpack.
	 */
	public void equipItem(Equipable itemToEquip) {
		if (!packItems.remove(itemToEquip))// removes the item from the non-equipped section of pack
			throw new InvalidBackpackException("Item was not in the backpack");
		if (equippedItems.size() >= MAX_EQUIPABLE_ITEMS)
			throw new InvalidBackpackException("Max amount of equipped items has been reached");

		equippedItems.add(itemToEquip);
		itemToEquip.provideBonus(owner);
	}

	/**
	 * Unequips this item, removing its given bonuses from the player it was
	 * equipped to and moving it out of the 'equipped' section of the backpack.
	 *
	 * @throws InvalidBackpackException
	 *             if the item was not equipped to any player or the pack's unequipped area is full.
	 */
	public void unequipItem(Equipable item) {
		if (!equippedItems.remove(item))// removes item from equiped section of pack
			throw new InvalidBackpackException("Item was not equipped");
		if (packItems.size() >= MAX_PACK_ITEMS)
			throw new InvalidBackpackException("Can't unequip as there are too many items in the pack");

		packItems.add(item);
		item.removeBonus(owner);
	}

	/**
	 * Uses this item on the player
	 *
	 * @param itemToUse
	 *            the item to be used
	 * @throws InvalidBackpackException
	 *             if the item was not part of a player's backpack.
	 */
	public void useItem(Usable itemToUse) {
		if (!packItems.remove(itemToUse))
			throw new InvalidBackpackException("Item was not found in the pack");

		itemToUse.use(owner);
	}

}

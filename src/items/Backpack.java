package items;

import java.util.ArrayList;
import java.util.List;

import player.Player;

/**
 * Backpack contains 2 sections, the player's inventory, and the items equipped
 * by the player. Main API for the Player class to use.
 *
 * @author Thomas Edwards
 *
 */
public class Backpack {
	/* Constants */
	public static final int MAX_EQUIPABLE_ITEMS = 2;
	public static final int MAX_INVENTORY = 20;

	private Player owner;
	private List<Item> inventory = new ArrayList<Item>(MAX_INVENTORY);
	private List<Equipable> equippedItems = new ArrayList<Equipable>(MAX_EQUIPABLE_ITEMS);
	private List<Key> keys = new ArrayList<Key>();

	/**
	 * Creates a new Backpack with the given owner
	 *
	 * @param owner
	 */
	public Backpack(Player owner) {
		this.owner = owner;
	}

	/**
	 * Adds the item to the player's backpack.
	 *
	 * @param item
	 *            the item to pick up
	 * @throws InvalidBackpackException
	 *             if the backpack is full or the item already belongs to the
	 *             player.
	 */
	public void pickUpItem(Item item) throws InvalidBackpackException {
		if (inventory.size() >= MAX_INVENTORY)
			throw new InvalidBackpackException("BackPack is full, can't add anymore items to it");
		if (item.getPack() != null)
			throw new InvalidBackpackException("Item has already been picked up");

		inventory.add(item);
		item.pickUp(this);
	}

	/**
	 * Picks up the item and immedietly uses it (removing the item from backpack).
	 * This method should be called when you want to interact with a 'switch\lever'
	 * type of item.
	 *
	 * @param itemToUse
	 * @throws InvalidBackpackException
	 */
	public void pickUpAndUse(Usable itemToUse) throws InvalidBackpackException {
		if (inventory.size() >= MAX_INVENTORY)
			throw new InvalidBackpackException("BackPack is full, can't add anymore items to it");
		if (itemToUse.getPack() != null)
			throw new InvalidBackpackException("Item has already been picked up");

		itemToUse.use(owner);
	}

	/**
	 * Removes the item from (the inventory section of) the player's backpack.
	 *
	 * @param item
	 *            the item to drop
	 * @throws InvalidBackpackException
	 *             if the backpack doesn't contain this item.
	 */
	public void removeItem(Item item) throws InvalidBackpackException {
		if (item.getPack() == null)
			throw new InvalidBackpackException("Item has not been picked up");
		if (!inventory.remove(item))
			throw new InvalidBackpackException(
					"Item was not in the backpack (Note. Item could be equipped in which case, unequip it, then drop it)");

		item.remove();// removes items link to backpack.
	}

	/**
	 * Equips this item, providing its given bonuses to the player, moving the item
	 * into the 'equipped' section of the player's backpack.
	 *
	 * @throws InvalidBackpackException
	 *             if the player already has the max number of items equipped or the
	 *             item is not part of a player's backpack.
	 */
	public void equipItem(Equipable item) throws InvalidBackpackException {
		if (!inventory.remove(item))// removes the item from the non-equipped section of pack
			throw new InvalidBackpackException("Item was not in the backpack");
		if (equippedItems.size() >= MAX_EQUIPABLE_ITEMS)
			throw new InvalidBackpackException("Max amount of equipped items has been reached");

		equippedItems.add(item);
		item.provideBonus(owner);
	}

	/**
	 * Unequips this item, removing its given bonuses from the player it was
	 * equipped to and moving it out of the 'equipped' section of the backpack.
	 *
	 * @throws InvalidBackpackException
	 *             if the item was not equipped to any player or the pack's
	 *             unequipped area is full.
	 */
	public void unequipItem(Equipable item) throws InvalidBackpackException {
		if (!equippedItems.remove(item))// removes item from equiped section of pack
			throw new InvalidBackpackException("Item was not equipped");
		if (inventory.size() >= MAX_INVENTORY)
			throw new InvalidBackpackException("Can't unequip as there are too many items in the inventory");

		inventory.add(item);
		item.removeBonus(owner);
	}

	/**
	 * Uses this item on the player and removes it from the inventory
	 *
	 * @param itemToUse
	 *            the item to be used
	 * @throws InvalidBackpackException
	 *             if the item was not part of a player's backpack.
	 */
	public void useItem(Usable itemToUse) throws InvalidBackpackException {
		if (!inventory.remove(itemToUse))// removes the item from inventory
			throw new InvalidBackpackException("Item was not found in the pack");

		itemToUse.use(owner);// uses the item.
	}

	public boolean checkDoorID(int doorID) {
		for (Key k : keys) {
			if (k.keyMatchesDoor(doorID))
				return true;
		}
		return false;
	}

}

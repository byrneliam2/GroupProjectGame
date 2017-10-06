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
	public static final int MAX_INVENTORY = 20;

	private Player owner;
	private List<Item> inventory = new ArrayList<Item>(MAX_INVENTORY);
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
	 * Adds the item to the player's backpack. If item is a key, adds it to the key
	 * section of the backpack.
	 *
	 * @param item
	 *            the item to pick up
	 * @throws InvalidBackpackException
	 *             if the backpack is full or the item already belongs to the player
	 *             or the item to be picked up is null.
	 */
	public void pickUpItem(Item item) throws InvalidBackpackException {
		if (item == null)
			throw new InvalidBackpackException("Can't pickup 'nothing' (closest item was null)");
		if (inventory.size() >= MAX_INVENTORY)
			throw new InvalidBackpackException("BackPack is full, can't add anymore items to it");
		if (item.getPack() != null)
			throw new InvalidBackpackException("Item has already been picked up");

		item.pickUp(this);
		if (item instanceof Key) {// if you pick up a key, add it to the key section
			Key key = (Key) item;
			keys.add(key); // FIXME: Can pick up infinite keys?
		} else if (item instanceof Equipable) {
			Equipable equip = (Equipable) item;
			equip.provideBonus(owner);
			inventory.add(item);
		} else if (item instanceof Usable) {
			Usable use = (Usable) item;
			use.use(owner);
		}
		
	}

	/**
	 * Picks up the item and immediately uses it (removing the item from backpack).
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

		item.setX(owner.getxLocation());
		item.setY(owner.getyLocation());
		item.remove();// removes items link to backpack.
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

	/**
	 * Compares the door id all of the keys in pack.
	 *
	 * @param doorID
	 * @return returns true if the given door id matches an id of a key contained in
	 *         the backpack.
	 */
	public boolean checkDoorID(int doorID) {
		for (Key k : keys) {
			if (k.keyMatchesDoor(doorID))
				return true;
		}
		return false;
	}

	public List<Item> getInventory() {
		return this.inventory;
	}


	public int getInventorySize() {
		return inventory.size();
	}
}

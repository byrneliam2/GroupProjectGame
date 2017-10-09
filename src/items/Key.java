package items;

import common.items.Item;

/**
 * Key Items unlock doors.
 *
 * @author Thomas Edwards
 *
 */
public class Key extends AbstractItem {

	private int id;
	private String color;

	public Key(int id, String color) {
		super("Key", "A key which which can unlock a door", "key");
		this.id = id;
		this.color = color;
	}

	/**
	 * @param doorID
	 * @return true if this key matches the doorID, false if it doesn't.
	 */
	public boolean keyMatchesDoor(int doorID) {
		return id == doorID;
	}

	public String getName() {
		return color + "Key";
	}

	public Item clone() {
		return new Key(this.id, this.color);
	}
}

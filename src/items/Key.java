package items;

<<<<<<< HEAD
import java.io.Serializable;
=======
import common.items.Item;
>>>>>>> 4276775ecaf2233d5d939e80508f781d1b738d9d

/**
 * Key Items unlock doors.
 *
 * @author Thomas Edwards
 *
 */
public class Key extends AbstractItem implements Serializable  {

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
	@Override
	public String getImageFileName() {
		return this.getName();
	}

	public Item clone() {
		return new Key(this.id, this.color);
	}
}

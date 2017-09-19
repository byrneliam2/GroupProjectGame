package items;

/**
 * Key Items unlock doors.
 *
 * @author Thomas Edwards
 *
 */
public class Key extends AbstractItem {

	private int id;

	public Key(int id) {
		super("Key", "A key which which can unlock a door", "key.png");
		this.id = id;
	}

	/**
	 * @param doorID
	 * @return true if this key matches the doorID, false if it doesn't.
	 */
	public boolean keyMatchesDoor(int doorID) {
		return id == doorID;
	}
}

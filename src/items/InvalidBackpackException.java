package items;

/**
 * A simple exception which simply means that the player is trying to do
 * something which is invalid. Such as pick up an item when their backpack is
 * full.
 *
 * @author Thomas Edwards
 *
 */
public class InvalidBackpackException extends Exception {

	public InvalidBackpackException(String message) {
		super(message);
	}

}

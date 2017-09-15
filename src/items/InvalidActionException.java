package items;

/**
 * A simple exception which simply means that the player is trying to do
 * something which is invalid. Such as pick up an item when their backpack is
 * full.
 *
 * @author edwardthom
 *
 */
public class InvalidActionException extends Exception {

	public InvalidActionException(String message) {
		super(message);
	}

}

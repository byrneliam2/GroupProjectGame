package player;

import java.io.Serializable;

/**
 * @author javahemohs Created by javahemohs on 19/09/17.
 *
 */
public class InvalidPlayerExceptions extends Exception implements Serializable  {
	public InvalidPlayerExceptions(String message) {
		super(message);
	}
}

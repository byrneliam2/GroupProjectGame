package map;

import java.io.Serializable;

/**
 * @author James
 *
 */
public class BadMapImageException extends Exception implements Serializable{
	public BadMapImageException(String mssg) {
		super(mssg);
	}
}

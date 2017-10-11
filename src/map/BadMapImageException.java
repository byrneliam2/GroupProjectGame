package map;

import java.io.Serializable;

public class BadMapImageException extends Exception implements Serializable  {
	public BadMapImageException(String mssg) {
		super(mssg);
	}
}

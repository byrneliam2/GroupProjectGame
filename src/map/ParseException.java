package map;

import java.io.Serializable;

/**
 * @author James
 *
 */
public class ParseException extends Exception implements Serializable{
	public ParseException(String msg) {
		super(msg);
	}
}

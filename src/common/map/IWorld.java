package common.map;

import java.util.HashMap;

import map.Map;
import map.World;

/**
 * This is an abstract world interface which describes all the functionality a
 * world should have.
 * 
 * @author James
 *
 */
public interface IWorld {

	/**
	 * This method returns the starting map of this world. This being the map the
	 * player starts on.
	 * 
	 * @return The starting map
	 */
	public Map getStartingMap();
	
	public HashMap<String, Map> getMaps();
}

package map;

import java.util.HashMap;

/**
 * This class represents a world. A world consists of a HashMap of map names to
 * maps and a HashMap of environment names to enviroment's
 *
 * @author James
 *
 */
public class World {

	// A HashMap of Map names to the map object that must be visible at all times,
	// thus is public static
	public static HashMap<String, Map> maps;

	public World(HashMap<String, Map> maps) {
		World.maps = maps;
	}

	/**
	 * Returns the starting map of the world. That map the player will first spawn
	 * into
	 *
	 * @return
	 */
	public Map getStartingMap() {
		return World.maps.get("Map3");
	}

	public static HashMap<String, Map> getMaps() {
		return maps;
	}
	
	
}

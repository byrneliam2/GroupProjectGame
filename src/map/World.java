package map;

import java.util.HashMap;

/**
 * This class represents a world. A world consists of a HashMap of map names to
 * maps and a HashMap of enviroment names to enviroment's
 * 
 * @author James
 *
 */
public class World {

	// A HashMap of Map names to the map object that must be visible at all times,
	// thus is public static
	public static HashMap<String, Map> maps;

	// A HashMap of enviroment names to enviroment objects that must be visible at
	// all times, thus is public static
	public static HashMap<String, Enviroment> enviroments;

	public World(HashMap<String, Map> maps, HashMap<String, Enviroment> enviroments) {
		this.maps = maps;
		this.enviroments = enviroments;
	}
}
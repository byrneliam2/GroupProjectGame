package map;

import java.io.Serializable;
import java.util.HashMap;

import common.map.IWorld;
import common.player.IPlayer;
import game.Game;

/**
 * This class represents a world. A world consists of a HashMap of map names to
 * maps and a HashMap of environment names to enviroment's
 *
 * @author James
 *
 */
public class World implements IWorld, Serializable {
	
	private HashMap<String, Map> maps;

	public World(HashMap<String, Map> maps) {
		this.maps = maps;
		for (Map m : maps.values()) {
			m.setWorld(this);
		}
	}

	/**
	 * Returns the starting map of the world. That map the player will first spawn
	 * into
	 *
	 * @return
	 */
	public Map getStartingMap() {
		for(String d : maps.keySet()) {
			if(d.charAt(3)=='3') {
				return this.maps.get(d);
			}
		}
		return null;
	}

	public HashMap<String, Map> getMaps() {
		return maps;
	}

}

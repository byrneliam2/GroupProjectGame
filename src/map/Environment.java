package map;

import player.Player;

/**
 * This class represents a type of environment that a tile in the map can be.
 * For example a mud environment.
 * 
 * @author James
 *
 */
public enum Environment {
	MIST(4), FIRE(3), MUD(2), DEATH(1);

	private final int EnvironmentCode;

	Environment(int code) {
		this.EnvironmentCode = code;
	}

	public int getEnviromentCode() {
		return EnvironmentCode;
	}

}

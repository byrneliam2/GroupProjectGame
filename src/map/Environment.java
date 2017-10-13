package map;

import java.io.Serializable;

import player.Player;

/**
 * This class represents a type of environment that a tile in the map can be.
 * For example a mud environment.
 *
 * @author James
 *
 */
public enum Environment implements Serializable{
	MIST(2), FIRE(3), MUD(4), DEATH(1);

	private final int EnvironmentCode;

	Environment(int code) {
		this.EnvironmentCode = code;
	}

	public int getEnviromentCode() {
		return EnvironmentCode;
	}

}

package JamesPackage;

/**
 * This class represents a type of enviroment that a tile in the map can be. For
 * example a mud enviroment. Each enviroment affect's the player in
 * one or multiple ways.
 * 
 * @author James
 *
 */
public interface Enviroment {

	void applyAffect(Player player);
}

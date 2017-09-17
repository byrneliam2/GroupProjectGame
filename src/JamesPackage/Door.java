package JamesPackage;

/**
 * This class represent a door, a door leads to another map. In this case each
 * door has the name of the map it leads to.
 * 
 * @author James
 *
 */
public class Door {

	private String map;

	public Door(String m) {
		this.map = m;
	}

	public String getMap() {
		return map;
	}

}

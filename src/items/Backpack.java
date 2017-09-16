package items;

/**
 * 
 * @author Thomas Edwards
 *
 */
public class Backpack {
	/* Constants */
	public static final int MAX_EQUIPABLE_ITEMS = 2;
	public static final int MAX_ITEMS = 16;// 14 in non-equipped section 2 in equipped section

	private Player owner;

	public Player getPlayer() {
		return this.owner;
	}

}

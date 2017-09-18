package items;

import player.Player;

public class Key extends AbstractItem {

	private int id;

	public Key(int id) {
		super("Key", "A key which which can unlock a door", "key.png");
		this.id = id;
	}

	public boolean keyMatchesDoor(int doorID) {
		return id == doorID;
	}
}

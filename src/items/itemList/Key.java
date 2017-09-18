package items.itemList;

import items.AbstractItem;
import items.Usable;
import player.Player;

public class Key extends AbstractItem implements Usable {

	public Key() {
		super("Key", "A key which when picked up, will immediatly unlock a door", "key.png");

	}

	@Override
	public void use(Player player) {

	}
}

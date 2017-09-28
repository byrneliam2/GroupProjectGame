package map;

import java.util.List;
import java.util.Scanner;

import items.Item;
import items.itemList.MassiveGun;

public class MassiveG {
	public MassiveG() {

	}

	public void parse(Scanner scan, List<Item> items) throws ParseException {
		Item gun = new MassiveGun();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		gun.setX(x * Map.tileSize);
		gun.setY(y * Map.tileSize);
		items.add(gun);
	}

}

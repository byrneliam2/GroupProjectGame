package map;

import java.util.List;
import java.util.Scanner;

import items.Item;
import items.itemList.RustyArmor;

public class RustyArm {
	public RustyArm() {

	}

	public void parse(Scanner scan, List<Item> items) throws ParseException {
		Item RustyA = new RustyArmor();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		RustyA.setX(x*Map.tileSize);
		RustyA.setY(y*Map.tileSize);
		items.add(RustyA);
	}
}

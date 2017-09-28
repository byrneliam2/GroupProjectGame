package map;

import java.util.List;
import java.util.Scanner;

import items.Item;
import items.itemList.ShinyArmor;

public class ShinyArm {
	public ShinyArm() {

	}

	public void parse(Scanner scan, List<Item> items) throws ParseException {
		Item ShinyArmor = new ShinyArmor();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		ShinyArmor.setX(x);
		ShinyArmor.setY(y);
		items.add(ShinyArmor);

	}
}

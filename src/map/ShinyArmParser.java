package map;

import java.util.List;
import java.util.Scanner;

import items.Item;
import items.itemList.ShinyArmor;

public class ShinyArmParser {
	public ShinyArmParser() {

	}

	public void parse(Scanner scan, List<Item> items) throws ParseException {
		Item ShinyArmor = new ShinyArmor();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		ShinyArmor.setX(x*Map.tileSize);
		ShinyArmor.setY(y*Map.tileSize);
		items.add(ShinyArmor);

	}
}
package map;

import java.util.List;
import java.util.Scanner;

import items.Item;
import items.itemList.HealthPot;

public class HealthPotParser {
	public HealthPotParser() {

	}

	public void parse(Scanner scan, List<Item> items) throws ParseException {
		Item hp = new HealthPot();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		hp.setX(x * Map.tileSize);
		hp.setY(y * Map.tileSize);
		items.add(hp);
	}
}

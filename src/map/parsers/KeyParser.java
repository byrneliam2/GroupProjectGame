package map.parsers;

import java.util.List;
import java.util.Scanner;

import common.items.Item;
import items.Key;
import map.Map;
import map.ParseException;

public class KeyParser {
	public KeyParser() {

	}

	public void parse(Scanner scan, List<Item> items) throws ParseException {
		int id = MapParser.requireInteger(scan);
		String color = scan.next();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);

		Item key = new Key(id,color);
		key.setX(x * Map.tileSize);
		key.setY(y * Map.tileSize);
		items.add(key);
	}
}

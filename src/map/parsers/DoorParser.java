package map.parsers;

import java.util.List;
import java.util.Scanner;

import items.DoorItem;
import map.Map;
import map.MapParser;
import map.ParseException;

public class DoorParser {
	public DoorParser() {

	}

	public void parse(Scanner scan, List<DoorItem> doors) throws ParseException {
		String name = MapParser.requireString(scan);
		int id = MapParser.requireInteger(scan);
		String locked = MapParser.requireString(scan);
		boolean lockDoor = false;
		if (locked.equals("true")) {
			lockDoor = true;
		}
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		DoorItem d = new DoorItem(name, id, lockDoor, x * Map.tileSize, y * Map.tileSize);
		doors.add(d);

	}
}

package map.parsers;

import java.util.List;
import java.util.Scanner;

import items.DoorItem;
import map.Map;
import map.MapParser;
import map.ParseException;

public class DoorParser {

	/**
	 * @param scan
	 * @param doors the list of doors to add this door item too.
	 * @throws ParseException
	 */
	public void parse(Scanner scan, List<DoorItem> doors) throws ParseException {
		String name = MapParser.requireString(scan);
		int id = MapParser.requireInteger(scan);
		String locked = MapParser.requireString(scan);
		boolean lockDoor = false;
		if (locked.equals("true")) {//lock the door if needed
			lockDoor = true;
		}
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		DoorItem d = new DoorItem(name, id, lockDoor, x * Map.tileSize, y * Map.tileSize);
		doors.add(d);

	}
}

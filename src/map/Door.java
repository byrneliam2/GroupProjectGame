package map;

import java.util.List;
import java.util.Scanner;

import items.DoorItem;

public class Door {
	public Door() {

	}

	public void parse(Scanner scan, List<DoorItem> doors) throws ParseException {
		String name = MapParser.requireString(scan);
		int id = MapParser.requireInteger(scan);
		String locked = MapParser.requireString(scan);
		boolean lockUnlock = false;
		if (locked.equals("true")) {
			lockUnlock = true;
		}
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		DoorItem d = new DoorItem(name, id, lockUnlock, x * Map.tileSize, y * Map.tileSize);
		doors.add(d);

	}
}

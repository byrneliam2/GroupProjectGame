package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.DoorItem;

public class Door {
	public Door() {

	}

	public void parse(Scanner scan, HashMap<DoorItem, Point> doors) throws ParseException {
		String name = MapParser.requireString(scan);
		int id = MapParser.requireInteger(scan);
		String locked = MapParser.requireString(scan);
		boolean lockUnlock = false;
		if (locked.equals("true")) {
			lockUnlock = true;
		}
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		DoorItem d = new DoorItem(name, id, lockUnlock);
		doors.put(d, new Point(x*32, y*32));

	}
}

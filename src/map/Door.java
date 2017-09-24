package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.DoorItem;

public class Door {
	public Door() {

	}

	public void parse(Scanner scan, HashMap<DoorItem, Point> doors) {
		String name = scan.next();
		int id = scan.nextInt();
		String locked = scan.next();
		boolean lockUnlock = false;
		if (locked.equals("true")) {
			lockUnlock = true;
		}
		int x = scan.nextInt();
		int y = scan.nextInt();
		DoorItem d = new DoorItem(name, id, lockUnlock);
		doors.put(d, new Point(x, y));

	}
}

package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;
import items.itemList.HealthPot;

public class MassiveGun {
	public MassiveGun() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item gun = (Item) new MassiveGun();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point gunLoc = new Point(x, y);
		items.put(gun, gunLoc);
	}

}

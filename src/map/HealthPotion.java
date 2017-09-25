package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;
import items.itemList.HealthPot;

public class HealthPotion {
	public HealthPotion() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item hp = new HealthPot();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point hpLoc = new Point(x*32, y*32);
		items.put(hp, hpLoc);
	}
}

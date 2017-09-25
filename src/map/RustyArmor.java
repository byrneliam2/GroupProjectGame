package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;

public class RustyArmor {
	public RustyArmor() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item RustyA = (Item) new RustyArmor();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point RustyAP = new Point(x, y);
		items.put(RustyA, RustyAP);
	}
}

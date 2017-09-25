package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;

public class RustyArm {
	public RustyArm() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item RustyA = (Item) new RustyArm();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point RustyAP = new Point(x*32, y*32);
		items.put(RustyA, RustyAP);
	}
}

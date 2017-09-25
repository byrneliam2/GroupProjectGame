package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;

public class ShinyArm {
	public ShinyArm() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item ShinyArmor = (Item) new ShinyArm();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point ShinyAP = new Point(x*32, y*32);
		items.put(ShinyArmor, ShinyAP);

	}
}

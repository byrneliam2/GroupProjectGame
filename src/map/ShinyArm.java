package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;
import items.itemList.ShinyArmor;

public class ShinyArm {
	public ShinyArm() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item ShinyArmor = new ShinyArmor();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point ShinyAP = new Point(x*32, y*32);
		items.put(ShinyArmor, ShinyAP);

	}
}

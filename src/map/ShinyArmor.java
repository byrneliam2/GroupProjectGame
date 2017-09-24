package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;

public class ShinyArmor {
	public ShinyArmor() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) {
		Item ShinyArmor = (Item) new ShinyArmor();
		int x = scan.nextInt();
		int y = scan.nextInt();
		Point ShinyAP = new Point(x, y);
		items.put(ShinyArmor, ShinyAP);
	}
}

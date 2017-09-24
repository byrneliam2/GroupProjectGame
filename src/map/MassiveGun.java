package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;
import items.itemList.HealthPot;

public class MassiveGun {
	public MassiveGun() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) {
		Item gun = (Item) new MassiveGun();
		int x = scan.nextInt();
		int y = scan.nextInt();
		Point gunLoc = new Point(x, y);
		items.put(gun, gunLoc);
	}

}

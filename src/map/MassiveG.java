package map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

import items.Item;
import items.itemList.MassiveGun;

public class MassiveG {
	public MassiveG() {

	}

	public void parse(Scanner scan, HashMap<Item, Point> items) throws ParseException {
		Item gun = new MassiveGun();
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		Point gunLoc = new Point(x*32, y*32);
		items.put(gun, gunLoc);
	}

}

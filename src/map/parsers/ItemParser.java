package map.parsers;

import java.util.List;
import java.util.Scanner;

import common.items.Item;
import items.itemList.HealthPot;
import items.itemList.MassiveGun;
import items.itemList.MaxHealthPot;
import items.itemList.RustyArmor;
import items.itemList.ShinyArmor;
import items.itemList.SmallGun;
import map.Map;
import map.ParseException;

public class ItemParser {

	public static void parseItem(String itemName, Scanner scan, List<Item> items) throws ParseException {
		if (itemName.equals("HealthPot")) {
			setItemPosition(scan,new HealthPot(),items);
		} else if (itemName.equals("MassiveGun")) {
			setItemPosition(scan,new MassiveGun(),items);
		} else if (itemName.equals("RustyArmor")) {
			setItemPosition(scan,new RustyArmor(),items);
		} else if (itemName.equals("ShinyArmor")) {
			setItemPosition(scan,new ShinyArmor(),items);
		}else if (itemName.equals("MaxHealthPot")) {
			setItemPosition(scan,new MaxHealthPot(),items);
		} else if (itemName.equals("SmallGun")) {
			setItemPosition(scan,new SmallGun(),items);
		}   else {
			throw new ParseException("Unrecognised Item or entity name");
		}
	}

	private static void setItemPosition(Scanner scan, Item i, List<Item> items) throws ParseException {
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		i.setX(x * Map.tileSize);
		i.setY(y * Map.tileSize);
		items.add(i);
	}

}

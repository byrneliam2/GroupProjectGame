package map;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import items.DoorItem;
import items.Item;
import npc.NPC;

/**
 * This class is responsible for reading a text file that contains all the
 * information for a single map. This includes reading various items. This class
 * can be considered a Map factory and thus has no referenceable constructor and
 * a parser method that can be accessed from anywhere.
 * 
 * @author James
 *
 */
public class MapParser {

	private MapParser() {

	}

	/**
	 * This method reads a map text file and returns a new map
	 */
	public static Map parse(String mapFileName) {
		String fileLocation = "../assets/maps/" + mapFileName;
		Scanner scan = null;
		File f = null;
		String mapName;
		HashMap<Item, Point> itms = new HashMap<Item, Point>();
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		HashMap<DoorItem, Point> doors = new HashMap<DoorItem, Point>();

		try {
			f = new File(fileLocation);
			scan = new Scanner(f);

			while (scan.hasNextLine()) {
				String line = scan.next();
				if (line.equals("HealthPot")) {
					new HealthPotion().parse(scan, itms);
				} else if (line.equals("MassiveGun")) {
					new MassiveGun().parse(scan, itms);
				} else if (line.equals("RustyArmor")) {
					new RustyArmor().parse(scan, itms);
				} else if (line.equals("ShinyArmor")) {
					new ShinyArmor().parse(scan, itms);
				} else if (line.equals("NPC")) {
					new ParseNPC().parse(scan, npcs, mainPLayer);
				} else if (line.equals("Door")) {
					new Door().parse(scan, doors);
				} else {
					throw new ParseException("Invalid text file");
				}

			}

			Map n = new Map(mapName, itms, npcs, doors);
			return n;

		} catch (IOException | ParseException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {

			if (scan != null) {
				scan.close();
			}
			if (f != null) {
				f.exists();
			}

		}
	}

	public String require(String token, Scanner scan) throws ParseException {
		if (scan.hasNext(token)) {
			return scan.next();
		} else {
			throw new ParseException("Was expecting the token " + token + "but instead received " + scan.next());
		}
	}

	public void requireSomething(Scanner scan) throws ParseException {
		if (!scan.hasNext()) {
			throw new ParseException("Was expecting another token but there was none");
		}
	}
}

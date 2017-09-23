package map;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
	private Map parse(String mapFileName) {
		String fileLocation = "../assets/maps/" + mapFileName;
		Scanner scan = null;
		File f = null;
		String mapName;
		HashMap<Item, Point> itms = new HashMap<Item, Point>();
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		HashMap<Item, Point> doors = new HashMap<Item, Point>();

		try {
			f = new File(fileLocation);
			scan = new Scanner(f);

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.equals("HealthPot")) {
					new HealthPotion().parse(scan, itms);
				} else if (line.equals("Yucky Bug")) {

				}

			}
			return null;

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (scan != null) {
				scan.close();
			}
			if (f != null) {
				f.exists();
			}

		}
		return null;
	}
}

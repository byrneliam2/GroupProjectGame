package map;

import java.util.ArrayList;
import java.util.Scanner;

import npc.ControlScheme;
import npc.EasyScheme;
import npc.HardScheme;
import npc.MediumScheme;
import npc.NPC;
import npc.PatrolScheme;
import npc.SprayScheme;
import npc.SuicidalScheme;
import player.Player;

public class NpcParser {
	public NpcParser() {

	}

	public void parse(Scanner scan, ArrayList<NPC> npcs, Player mainPLayer) throws ParseException {
		String name = MapParser.requireString(scan);
		String scheme = MapParser.requireString(scan);
		String leftRight = MapParser.requireString(scan);
		int patrolDistance = MapParser.requireInteger(scan);
		int x = MapParser.requireInteger(scan);
		int y = MapParser.requireInteger(scan);
		int health = MapParser.requireInteger(scan);
		Boolean LR = false;
		if (leftRight.equals("true")) {
			LR = true;
		}
		ControlScheme a = null;
		if (scheme.equals("PatrolScheme")) {
			a = new PatrolScheme(LR, patrolDistance);
		} else if (scheme.equals("EasyScheme")) {
			a = new EasyScheme();
		} else if (scheme.equals("MediumScheme")) {
			a = new MediumScheme();
		} else if (scheme.equals("HardScheme")) {
			a = new HardScheme();
		} else if (scheme.equals("SuicidalScheme")) {
			a = new SuicidalScheme();
		} else if (scheme.equals("SprayScheme")) {
			a = new SprayScheme(patrolDistance);
		} else {
			throw new ParseException("Your file has a incorrect Control Scheme: " + scheme);
		}
		NPC n = new NPC(name, x * 32, y * 32, health, mainPLayer, a);
		npcs.add(n);
	}
}

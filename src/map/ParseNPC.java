package map;

import java.util.ArrayList;
import java.util.Scanner;

import npc.ControlScheme;
import npc.NPC;
import npc.PatrolScheme;
import player.Player;

public class ParseNPC {
	public ParseNPC() {

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
		}
		NPC n = new NPC(name, x, y, health, mainPLayer, a);
		npcs.add(n);
	}
}

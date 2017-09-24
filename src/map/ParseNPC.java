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

	public void parse(Scanner scan, ArrayList<NPC> npcs, Player mainPLayer) {
		String name = scan.next();
		String scheme = scan.next();
		String leftRight = scan.next();
		int patrolDistance = scan.nextInt();
		int x = scan.nextInt();
		int y = scan.nextInt();
		Boolean LR = false;
		if (leftRight.equals("true")) {
			LR = true;
		}
		ControlScheme a = new PatrolScheme(LR, patrolDistance);
		NPC n = new NPC(name, x, y, mainPLayer, a);
		npcs.add(n);
	}
}

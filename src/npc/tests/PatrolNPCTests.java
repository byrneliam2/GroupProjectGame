package npc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import map.Map;
import npc.NPC;
import npc.PatrolScheme;
import player.Player;

public class PatrolNPCTests {

	private ArrayList<NPC> npcs;
	private Player p;
	private Map m;
	private NPC testNPC;

	/**
	 * Tests that the patrol npc moves 3 units.
	 */
	@Test
	public void testPatrol1() {
		int x = 500, y = 400;
		setup(x, y, 1);
		int numberOfMoves = 3;// private int the number of moves that the scheme should make

		testNPC.start();
		for (int i = 1; i < numberOfMoves; i++) {
			try {
				Thread.sleep(NPC.SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
				fail();
			}
		}
		testNPC.stop();

		assertEquals(x + numberOfMoves, testNPC.getxLocation());// NPC should have moved 3 pixels right.
		assertEquals(y, testNPC.getyLocation());
	}

	/**
	 * Tests that the patrol npc turns around after one tile.
	 */
	@Test
	public void testPatrol2() {
		int x = 500, y = 400;
		setup(x, y, 1);
		int numberOfMoves = Map.tileSize;// private int the number of moves that the scheme should make

		testNPC.start();
		sleep(numberOfMoves);
		testNPC.stop();

		assertEquals(x + Map.tileSize, testNPC.getxLocation());// NPC should have moved one map spot across.
		assertEquals(y, testNPC.getyLocation());

		// at this point, the next move should move the NPC back the way it came from.
		testNPC.start();
		try {
			Thread.sleep(NPC.SPEED / 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testNPC.stop();
		assertEquals(x + Map.tileSize - 1, testNPC.getxLocation());// NPC should have moved one map spot across.
		assertEquals(y, testNPC.getyLocation());
	}

	/**
	 * Sleeps for the amount of time taken to do x number of npc moves...
	 * 
	 * DONT use 1, as it wont sleep for anything.
	 * 
	 * @param numberOfMoves
	 *            any number greater than 1.
	 */
	public void sleep(int numberOfMoves) {
		for (int i = 1; i < numberOfMoves; i++) {
			try {
				Thread.sleep(NPC.SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
				fail();
			}
		}
	}

	public void setup(int npcX, int npcY, int patrolDuration) {
		npcs = new ArrayList<NPC>();
		p = new Player("", 5, 5);
		m = new Map("Map3", p, null, npcs, null);
		p.setMap(m);
		testNPC = new NPC("bug", npcX, npcY, 1, p, new PatrolScheme(true, patrolDuration));
		npcs.add(testNPC);
	}

}

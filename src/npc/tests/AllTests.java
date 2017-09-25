package npc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import map.Map;
import npc.NPC;
import npc.PatrolScheme;
import player.Player;

public class AllTests {

	private ArrayList<NPC> npcs;
	private Player p;
	private Map m;

	/**
	 * Tests that the patrol npc moves 3 units.
	 */
	@Test
	public void testPatrol1() {
		setup();
		NPC testNPC = new NPC("bug", 5, 10, 1, p, new PatrolScheme(true, 5));
		npcs.add(testNPC);
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

		assertEquals(8, testNPC.getxLocation());// NPC should have moved 3 pixels right.
		assertEquals(10, testNPC.getyLocation());
	}

	/**
	 * Tests that the patrol npc turns around after one tile.
	 */
	@Test
	public void testPatrol2() {
		setup();
		NPC testNPC = new NPC("bug", 5, 10, 1, p, new PatrolScheme(true, 1));
		npcs.add(testNPC);
		int numberOfMoves = Map.tileSize;// private int the number of moves that the scheme should make

		testNPC.start();
		sleep(numberOfMoves);
		testNPC.stop();

		assertEquals(5 + Map.tileSize, testNPC.getxLocation());// NPC should have moved one map spot across.
		assertEquals(10, testNPC.getyLocation());

		// at this point, the next move should move the NPC back the way it came from.
		testNPC.start();
		try {
			Thread.sleep(NPC.SPEED / 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testNPC.stop();
		assertEquals(5 + Map.tileSize - 1, testNPC.getxLocation());// NPC should have moved one map spot across.
		assertEquals(10, testNPC.getyLocation());
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

	public void setup() {
		npcs = new ArrayList<NPC>();
		p = new Player("", 5, 5);
		m = new Map("Map1", null, npcs, null);
	}

}

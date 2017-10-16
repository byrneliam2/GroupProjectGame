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
	 * Tests that the patrol npc moves downwards.
	 */
	@Test
	public void testPatrol1() {
		int x = 500, y = 400;
		setup(x, y, 1);

		testNPC.start();// starts the npc off
		try {// sleep for time it takes to move 3 times
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}

		testNPC.stop();// stops the npc

		// checks the npc's position
		assertTrue(testNPC.getxLocation()>x);
		assertEquals(y+1, testNPC.getyLocation());
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
				Thread.sleep(NPC.updateRate);
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
		testNPC.setMap(m);
		npcs.add(testNPC);
	}

}

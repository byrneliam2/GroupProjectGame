package npc.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import npc.NPC;
import npc.PatrolScheme;
import player.Player;

/**
 * Test that the correct direction is gotten by the getAngleToPlayer method.
 * 
 * @author Thomas Edwards
 *
 */
public class DirectionTests {

	@Test
	public void testDirection1() {
		Player p = new Player("", 20, 20);
		NPC n = new NPC("bug", 25, 25, 1, p, new PatrolScheme(true, 5));

		assertEquals(7 * Math.PI / 4, n.getAngleToPlayer(), 0);
	}

	@Test
	public void testDirection2() {
		Player p = new Player("", 25, 25);
		NPC n = new NPC("bug", 20, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(3 * Math.PI / 4, n.getAngleToPlayer(), 0);
	}

	@Test
	public void testDirection3() {
		Player p = new Player("", 15, 25);
		NPC n = new NPC("bug", 20, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(5 * Math.PI / 4, n.getAngleToPlayer(), 0);
	}

	@Test
	public void testDirection4() {
		Player p = new Player("", 20, 20);
		NPC n = new NPC("bug", 15, 25, 1, p, new PatrolScheme(true, 5));

		assertEquals(1 * Math.PI / 4, n.getAngleToPlayer(), 0);
	}

}

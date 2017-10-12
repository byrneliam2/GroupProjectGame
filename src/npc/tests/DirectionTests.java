package npc.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import npc.NPC;
import npc.PatrolScheme;
import player.Player;
import common.utils.MathUtils;

/**
 * Test that the correct direction is gotten by the Math.utils.CalculateAngle()
 * method. Tests the 8 coordinates of a compass. Tested in this package as this
 * is where the method is primarily used.
 * 
 * @author Thomas Edwards
 *
 */
public class DirectionTests {

	@Test
	public void testDirectionNW() {
		Player p = new Player("", 20, 20);
		NPC n = new NPC("bug", 25, 25, 1, p, new PatrolScheme(true, 5));

		assertEquals(7 * Math.PI / 4,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionSE() {
		Player p = new Player("", 25, 25);
		NPC n = new NPC("bug", 20, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(3 * Math.PI / 4,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionSW() {
		Player p = new Player("", 15, 25);
		NPC n = new NPC("bug", 20, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(5 * Math.PI / 4,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionNE() {
		Player p = new Player("", 20, 20);
		NPC n = new NPC("bug", 15, 25, 1, p, new PatrolScheme(true, 5));

		assertEquals(1 * Math.PI / 4,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionNorth() {
		Player p = new Player("", 20, 20);
		NPC n = new NPC("bug", 20, 25, 1, p, new PatrolScheme(true, 5));

		assertEquals(0,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionSouth() {
		Player p = new Player("", 20, 25);
		NPC n = new NPC("bug", 20, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(Math.PI,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionEast() {
		Player p = new Player("", 25, 20);
		NPC n = new NPC("bug", 20, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(Math.PI / 2,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

	@Test
	public void testDirectionWest() {
		Player p = new Player("", 20, 20);
		NPC n = new NPC("bug", 25, 20, 1, p, new PatrolScheme(true, 5));

		assertEquals(3 * Math.PI / 2,
				MathUtils.calculateAngle(n.getxLocation(), n.getyLocation(), p.getxLocation(), p.getyLocation()), 0);
	}

}

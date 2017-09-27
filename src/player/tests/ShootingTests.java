package player.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import map.Map;
import npc.NPC;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;

/**
 * @author Thomas Edwards
 *
 */
public class ShootingTests {
	private ArrayList<NPC> npcs;
	private Player p;
	private Map m;
	private int xLocation = 100;
	private int yLocation = 100;
	private double mouseX = 50.50;
	private double mouseY = 50.50;

	/**
	 * Tests shooting a bullet.
	 */
	@Test
	public void testShooting1() {
		setup();
		try {
			p.shoot(mouseX, mouseY);
		} catch (InvalidPlayerExceptions e) {
			fail("The Function didnt work");
		}
	}

	/**
	 * Tests shooting twice immediately, should throw exception as player can only
	 * shoot once per second.
	 */
	@Test
	public void testShooting2() {
		setup();
		try {
			p.shoot(mouseX, mouseY);
			p.shoot(mouseX, mouseY);
			fail("player should only be able to shoot once per second as a deafault");
		} catch (InvalidPlayerExceptions e) {
		}
	}

	/**
	 * Tests that you can shoot twice with 1.2 second wait between shots.
	 */
	@Test
	public void testShooting3() {
		setup();
		try {
			p.shoot(mouseX, mouseY);
			sleep(1200);
			p.shoot(mouseX, mouseY);
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	/**
	 * Tests that the bullet List contains the correct info after creating a bullet
	 */
	@Test
	public void testShooting4() {
		setup();
		try {
			p.shoot(mouseX, mouseY);
			assertEquals(1, Bullet.bulletList.size());
			sleep(1100);
			p.shoot(mouseX, mouseY);
			assertEquals(2, Bullet.bulletList.size());
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	/**
	 * Tests that the bullets are starting at roughly the player's location.
	 */
	@Test
	public void testShooting5() {
		setup();
		try {
			p.shoot(mouseX, mouseY);
			Bullet b = Bullet.bulletList.get(0);
			double x = b.getX();
			double y = b.getY();

			// checks that the bullet is starting at the players location.
			assertEquals(this.xLocation, x, 2);
			assertEquals(this.yLocation, y, 2);
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	/**
	 * Tests that the bullets are moving in roughly the right direction.
	 */
	@Test
	public void testDir1() {
		setup();
		try {
			p.shoot(mouseX, mouseY);
			Bullet b = Bullet.bulletList.get(0);
			double x = b.getX();
			double y = b.getY();

			sleep(300);

			assertTrue(b.getX() < x);
			assertTrue(b.getY() < y);
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	/**
	 * Tests that the bullets are moving in roughly the right direction.
	 */
	@Test
	public void testDir2() {
		setup();
		try {
			p.shoot(130, 140);
			assertEquals(1, Bullet.bulletList.size());
			Bullet b = Bullet.bulletList.get(0);
			double x = b.getX();
			double y = b.getY();

			sleep(300);

			assertTrue(b.getX() > x);
			assertTrue(b.getY() > y);
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	/**
	 * Tests that the bullets are moving in roughly the right direction.
	 */
	@Test
	public void testDir3() {
		setup();
		try {
			p.shoot(50, 120);
			assertEquals(1, Bullet.bulletList.size());
			Bullet b = Bullet.bulletList.get(0);
			double x = b.getX();
			double y = b.getY();

			sleep(300);

			assertTrue(b.getX() < x);
			assertTrue(b.getY() > y);
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	/**
	 * Tests that the bullets are moving in roughly the right direction.
	 */
	@Test
	public void testDir4() {
		setup();
		try {
			p.shoot(120, 50);
			assertEquals(1, Bullet.bulletList.size());
			Bullet b = Bullet.bulletList.get(0);
			double x = b.getX();
			double y = b.getY();

			sleep(300);

			assertTrue(b.getX() > x);
			assertTrue(b.getY() < y);
		} catch (InvalidPlayerExceptions e) {
			fail();
		}
	}

	public void setup() {
		Bullet.bulletList.clear();
		p = new Player("Thomas", xLocation, yLocation);
		npcs = new ArrayList<NPC>();
		m = new Map("Map1", p, null, npcs, null);
		p.setMap(m);
	}

	/**
	 * @param duration
	 *            in milliseconds
	 */
	public void sleep(int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}
	}
}

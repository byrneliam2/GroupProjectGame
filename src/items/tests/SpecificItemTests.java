package items.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import items.itemList.*;
import player.InvalidPlayerExceptions;
import player.Player;
import items.InvalidBackpackException;

public class SpecificItemTests {

	/**
	 * Tests that using the health pot removes it from inventory
	 */
	@Test
	public void testUsingHealthPot1() {
		HealthPot i = new HealthPot();
		Player p = new Player("tom", 5, 5);

		try {
			p.getBackpack().pickUpItem(i);
			assertTrue(p.getBackpack().getInventory().contains(i));
			p.useItem(i);
			assertFalse(p.getBackpack().getInventory().contains(i));// item should dissapear once used
		} catch (InvalidBackpackException | InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests that using a healthPot on full health doesn't make the player go over max health
	 */
	@Test
	public void testUsingHealthPot2() {
		HealthPot i = new HealthPot();
		Player p = new Player("tom", 5, 5);

		try {
			p.getBackpack().pickUpItem(i);
			assertTrue(p.getBackpack().getInventory().contains(i));
			assertEquals("Player should be on max health", p.getMaxHealth(), p.getHealth());

			p.useItem(i);
			assertEquals("Player should still be on max health", p.getMaxHealth(), p.getHealth());
		} catch (InvalidBackpackException | InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests using health potion restores 1 health.
	 */
	@Test
	public void testUsingHealthPot3() {
		HealthPot i = new HealthPot();
		Player p = new Player("tom", 5, 5);

		try {
			p.getBackpack().pickUpItem(i);
			assertTrue(p.getBackpack().getInventory().contains(i));
			// manually sets the player's health
			p.setHealth(2);
			p.setMaxHealth(5);
			p.useItem(i);
			assertEquals("Player should have one more health", 3, p.getHealth());
		} catch (InvalidBackpackException | InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests using 2 health potions restores 1 health twice.
	 */
	@Test
	public void testUsingHealthPot4() {
		HealthPot i = new HealthPot();
		HealthPot i2 = new HealthPot();
		Player p = new Player("tom", 5, 5);

		try {
			p.getBackpack().pickUpItem(i);
			p.getBackpack().pickUpItem(i2);
			assertTrue(p.getBackpack().getInventory().contains(i));
			assertTrue(p.getBackpack().getInventory().contains(i2));
			// manually sets the player's health
			p.setHealth(2);
			p.setMaxHealth(5);

			p.useItem(i);
			assertEquals("Player should have one more health", 3, p.getHealth());
			assertFalse(p.getBackpack().getInventory().contains(i));

			p.useItem(i2);
			assertEquals("Player should have one more health", 4, p.getHealth());
			assertFalse(p.getBackpack().getInventory().contains(i2));
		} catch (InvalidBackpackException | InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
	}

}

package items.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import items.itemList.*;
import player.InvalidPlayerExceptions;
import player.Player;
import items.InvalidBackpackException;

public class specificItemTests {

	@Test
	public void testHealthPot() {
		HealthPot i = new HealthPot();
		Player p = new Player("tom", 5, 5);
		try {
			p.getBackpack().pickUpItem(i);
			assertTrue(p.getBackpack().getInventory().contains(i));
			p.useItem(i);
			assertFalse(p.getBackpack().getInventory().contains(i));
		} catch (InvalidBackpackException e) {
			e.printStackTrace();
			fail();
		} catch (InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
	}

}

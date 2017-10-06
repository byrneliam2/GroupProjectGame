package items.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import items.Backpack;
import items.InvalidBackpackException;
import common.items.Item;
import items.Usable;
import items.itemList.HealthPot;
import player.Player;

public class BackPackTests {

	@Test
	public void testPickup() {
		Item i = new HealthPot();
		Backpack b = new Backpack(null);
		try {
			b.pickUpItem(i);
		} catch (InvalidBackpackException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Tests that the backpack can pickup and use a usable item
	 */
	@Test
	public void testPickupAndUse() {
		Item i = new HealthPot();
		Player p = new Player("tom", 5, 5);
		Backpack b = new Backpack(p);

		try {
			Usable u = (Usable) i;
			b.pickUpAndUse(u);
		} catch (InvalidBackpackException e) {
			e.printStackTrace();
			fail();
		}
	}

}

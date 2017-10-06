package items.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.items.Item;
import items.*;
import items.itemList.HealthPot;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import player.Player;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExternalItemsTests {

	@Test
	public void test01_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		assertTrue(b.getInventory().isEmpty());
		// assertTrue(b.getEquippedItems().isEmpty());
		assertTrue(b.getInventory().size() == b.getInventorySize());
	}

	@Test
	public void test02_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		try {
			b.pickUpItem(null);
			fail("Shouldn't be able to pick up null!");
		} catch (InvalidBackpackException ignored) {
		}
	}

	@Test
	public void test03_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		for (int i = 0; i < Backpack.MAX_INVENTORY; i++)
			try {
				b.pickUpItem(new HealthPot());
			} catch (InvalidBackpackException e) {
				e.printStackTrace();
			}
		try {
			b.pickUpItem(new HealthPot());
			fail("Shouldn't be able to pick up item when bag is full!");
		} catch (InvalidBackpackException ignored) {
		}
	}

	@Test
	public void test04_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		try {
			Item i = new Key(0);
			b.pickUpItem(i);
			b.pickUpItem(i);
			fail("Can't pick up the same item twice!");
		} catch (InvalidBackpackException ignored) {
		}
	}

	@Test
	public void test05_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		for (int i = 0; i < Backpack.MAX_INVENTORY; i++)
			try {
				b.pickUpItem(new HealthPot());
			} catch (InvalidBackpackException e) {
				e.printStackTrace();
			}
		try {
			b.pickUpAndUse(new HealthPot());
			fail("Shouldn't be able to pick up item when bag is full!");
		} catch (InvalidBackpackException ignored) {
		}
	}

	@Test
	public void test06_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		try {
			Usable u = new HealthPot();
			b.pickUpItem(u);
			b.pickUpAndUse(u);
			fail("Can't pick up the same item twice!");
		} catch (InvalidBackpackException ignored) {
		}
	}

	@Test
	public void test07_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		Item i = new HealthPot();
		try {
			b.pickUpItem(i);
			b.removeItem(i);
			assertTrue(i.getX() == 50);
			assertTrue(i.getY() == 50);
			assertTrue(i.getPack() == null);
			assertTrue(b.getInventorySize() == 0);
		} catch (InvalidBackpackException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test08_BackpackTests() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		Item i = new HealthPot();
		try {
			b.removeItem(i);
			fail("Can't remove item that doesn't exist in pack.");
		} catch (InvalidBackpackException ignored) {
		}
	}

	/*
	 * @Test
	 * public void test09_BackpackTests() {
	 * Player p = new Player("Player", 50, 50);
	 * Backpack b = new Backpack(p);
	 * Equipable eq = new MassiveGun();
	 * try {
	 * b.pickUpItem(eq);
	 * b.equipItem(eq);
	 * assertTrue(b.getInventorySize() == 0);
	 * assertTrue(b.getEquippedItems().size() == 1);
	 * assertTrue(p.getFireRate() == 0.5);
	 * } catch (InvalidBackpackException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * 
	 * @Test
	 * public void test10_BackpackTests() {
	 * Player p = new Player("Player", 50, 50);
	 * Backpack b = new Backpack(p);
	 * Equipable eq = new MassiveGun();
	 * try {
	 * b.pickUpItem(eq);
	 * b.equipItem(eq);
	 * b.unequipItem(eq);
	 * assertTrue(b.getInventorySize() == 1);
	 * assertTrue(b.getEquippedItems().size() == 0);
	 * assertTrue(p.getFireRate() == p.getDefaultFireRate());
	 * } catch (InvalidBackpackException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * 
	 * @Test
	 * public void test11_BackpackTests() {
	 * Player p = new Player("Player", 50, 50);
	 * Backpack b = new Backpack(p);
	 * Equipable eq = new ShinyArmor();
	 * try {
	 * b.equipItem(eq);
	 * fail("Cannot equip item that the player does not have.");
	 * } catch (InvalidBackpackException ignored) {}
	 * try {
	 * b.pickUpItem(eq);
	 * b.equipItem(eq);
	 * Equipable r1, r2;
	 * b.pickUpItem(r1 = new RustyArmor());
	 * b.equipItem(r1);
	 * b.pickUpItem(r2 = new ShinyArmor());
	 * b.equipItem(r2);
	 * fail("Cannot equip item when both slots are full.");
	 * } catch (InvalidBackpackException ignored) {}
	 * }
	 * 
	 * @Test
	 * public void test12_BackpackTests() {
	 * Player p = new Player("Player", 50, 50);
	 * Backpack b = new Backpack(p);
	 * Equipable eq = new RustyArmor();
	 * try {
	 * b.unequipItem(eq);
	 * fail("Cannot unequip item that the player does not have.");
	 * } catch (InvalidBackpackException ignored) {}
	 * try {
	 * b.pickUpItem(eq);
	 * b.equipItem(eq);
	 * for (int i = 0; i < Backpack.MAX_INVENTORY; i++) b.pickUpItem(new HealthPot());
	 * b.unequipItem(eq);
	 * fail("Cannot unequip item when inventory is full.");
	 * } catch (InvalidBackpackException ignored) {}
	 * }
	 */
	@Test
	public void test13_KeyID() {
		Player p = new Player("Player", 50, 50);
		Backpack b = new Backpack(p);
		for (int i = 0; i < Backpack.MAX_INVENTORY; i++)
			try {
				b.pickUpItem(new Key(i));
			} catch (InvalidBackpackException e) {
				e.printStackTrace();
			}
		assertTrue(b.checkDoorID(15));
		assertFalse(b.checkDoorID(22));
	}

	@Test
	public void test14_DoorItems() {
		DoorItem d = new DoorItem("nomap", 0, true, 100, 0);
		assertTrue(d.isLocked());
		assertTrue(d.getMap().equals("nomap"));
		assertTrue(d.getDoorID() == 0);
	}

	@Test
	public void test15_DoorItems() {
		DoorItem d = new DoorItem("nomap", 0, true, 100, 0);
		d.unlockDoor();
		assertFalse(d.isLocked());
	}

}

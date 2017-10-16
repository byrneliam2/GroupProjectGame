package player.tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import common.items.Item;
import common.map.IMap;
import common.mocks.MockMap;
import items.Backpack;
import items.InvalidBackpackException;
import items.Usable;
import items.itemList.*;
import map.Map;
import npc.NPC;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;


/**
 * @author Mohsen Javaher
 *
 */
public class PlayerTest {
	private Map m;
	private String name = "Thomas";
	private int xLocation = 100;
	private int yLocation = 100;
	private double mouseX = 50.50;
	private double mouseY = 50.50;
	private ArrayList<NPC> npcs;
	private Player player = new Player(name, xLocation, yLocation);


	/**
	 * Check if when a player picks up an item should appear in the itemsList OR the BackPack.
	 * @throws InvalidPlayerExceptions
	 * @throws InvalidBackpackException
	 */
	@Test
	public void testPickedUpItem() throws InvalidPlayerExceptions, InvalidBackpackException {
		// insert a player with specific coordinate
		player = new Player(name, xLocation, yLocation);
		IMap m = new MockMap();
		// insert an item with specific coordinate next to the player
		Item i = new MassiveGun();
		m.placeItem(i, 100, 100);
		// use the pick up item from the player class
		player.getBackpack().pickUpItem(i);
		// if the player backpack contains the specific item assertFalse
		// because the HealthPot is a useable type of item so the backpack would keep usable items
		// it just increases up the health of the player
		assertTrue(player.getBackpack().getInventory().contains(i));
		// else assertFalse
	}

	/**
	 * Check if when a player removes an item from the BackPack should NOT appear in the itemsList OR the BackPack anymore.
	 * @throws InvalidBackpackException
	 */
	@Test
	@Deprecated
	public void testRemoveItem() throws InvalidBackpackException {
		// insert a player with specific coordinate
		// insert an item with specific coordinate next to the player
		// use the pick up item from the player class
		// if the player backpack contains the specific item
			// then use the remove method to delete the item.
			// if the player backpack doesnt contains the item
					// assertTrue
			// else
					//assertFalse
	}


	/**
	 * Check if the Player is in rangeCircle box then it can pick up the item Other than that the test fails
	 * @throws InvalidBackpackException
	 */
	@Test
	public void testRangeCircle() throws InvalidBackpackException {
		player = new Player(name, xLocation, yLocation);
		// insert an item with specific coordinate next to the player
		Item i = new MassiveGun();
		IMap m = new MockMap();
		m.placeItem(i, 95, 95);
		// use the pick up item from the player class
		player.getBackpack().pickUpItem(i);
		// if the player backpack contains the specific item assertTrue
		assertTrue(player.getBackpack().getInventory().contains(i));
		// else assertFalse
	}

	/**
	 * Check if the defaultFireRate of the player decreases when the Player picks up a gun.
	 * @throws InvalidBackpackException
	 */
	@Test
	public void testDefaultFireRate() throws InvalidBackpackException {
		player = new Player(name, xLocation, yLocation);
		// insert an item with specific coordinate next to the player
		Item i = new MassiveGun();
		m.placeItem(i, 95, 95);
		// use the pick up item from the player class
		double fireRateTemp = player.getFireRate();
		player.getBackpack().pickUpItem(i);
		// check if the player backpack contatins the gun
		if (player.getBackpack().getInventory().contains(i)) {
			// if it does contain the gun
			// if the fire rate should have increased assertTrue
			assertTrue((fireRateTemp<player.getFireRate()));
		}
	}

	/**
	 * Check if when a player takes damage When an enemy has shooted around the playerBox.
	 * The Player has a box which has been implemented by using the Rectangle class. Which is kind
	 * of the hit box representing the location of the player.
	 */
	@Test
	public void testTakeDamage() {
		// insert a player with specific coordinate
		player = new Player(name, xLocation, yLocation);
		// The player initially has 5 hearts
		// if the player loses two of them then he has 3 hearts
		player.takeDamage();
		player.takeDamage();
		// by using the getHealth funtion check if the take damage works properly
		assertTrue(player.getHealth() == 3);
	}

	/**
	 * Check if the Player has the specific key to open the door and the player should be around the door to open the door
	 */
	@Test
	public void testCanOpenDoor() {
		// insert a player with specific coordinate
		// insert a door with those specific coordinates too
		// insert keys which Player can pick them up
		// if the player back pack contains the keys
			// then if the player can move to the door
				//assertTrue();
			// else
				//assertFalse();
	}


	/**
	 * Check if the player can not walk through the walls and the doors if the player doesnt have the key.
	 */
	@Test
	public void testCanMove() {

	}

	/**
	 * Check if the player can not walk through the walls and the doors if the player doesnt have the key.
	 */
	@Test
	public void tesDeadthPlayer() {
		// insert a player with specific coordinate
		player = new Player(name, xLocation, yLocation);
		// initially player has 5 hearts
		for(int i =0; i<5; i++) {
			// attack the player for 5 times
			player.takeDamage();
			// check if the the player heart is equall to zero
			if(player.getHealth() == 0) {
				// then the isDead flag for the player has turned to true
				assertTrue(player.isDead());
			}
		}
	}
}
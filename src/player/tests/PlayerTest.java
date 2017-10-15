package player.tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import common.items.Item;
import common.map.IMap;
import common.mocks.MockMap;
import items.Backpack;
import items.DoorItem;
import items.InvalidBackpackException;
import items.Key;
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
	 * Check if when a player removes an item from the BackPack should NOT appear in
	 * the itemsList OR the BackPack anymore.
	 * 
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
		// assertFalse
	}

	/**
	 * Check if the Player is in rangeCircle box then it can pick up the item Other
	 * than that the test fails
	 * 
	 * @throws InvalidBackpackException
	 */
	@Test
	public void testRangeCircle() throws InvalidBackpackException {
		player = new Player(name, xLocation, yLocation);
		// insert an item with specific coordinate next to the player
		Item i = new MassiveGun();
		// Create a gun Item with specific coordinates next to the player
		IMap m = new MockMap();
		m.placeItem(i, 95, 95);
		// use the pick up item from the player class
		player.getBackpack().pickUpItem(i);
		// if the player backpack contains the specific item assertTrue
		assertTrue(player.getBackpack().getInventory().contains(i));
		// else assertFalse
	}

	/**
	 * Check if the defaultFireRate of the player decreases when the Player picks up
	 * a gun.
	 * @throws InvalidBackpackException 
	 */
	@Test
	public void testDefaultFireRate() throws InvalidBackpackException {
		player = new Player(name, xLocation, yLocation);
		// insert an item with specific coordinate next to the player
		Item i = new MassiveGun();
		// Create a gun Item with specific coordinates next to the player
		IMap m = new MockMap();
		// Create a gun Item with specific coordinates next to the player
		// player needs to pick up the gun
		m.placeItem(i, 98, 98);
		// use the pick up item from the player class
		double tempFireRate = player.getFireRate();
		player.getBackpack().pickUpItem(i);
		// check if the player backpack contatins the gun
		if(player.getBackpack().getInventory().contains(i)){
			// if it does contain the gun
			// if the fire rate should have increased
			// assertTrue
			assertTrue((tempFireRate < player.getFireRate()));
		}
	}

	/**
	 * Check if when a player takes damage When an enemy has shooted around the
	 * playerBox. The Player has a box which has been implemented by using the
	 * Rectangle class. Which is kind of the hit box representing the location of
	 * the player.
	 */
	@Test
	public void testTakeDamage() {
		// Create a Player with specific coordinates
		player = new Player(name, xLocation, yLocation);
		// initially the player has 5 hearts
		// so if the player gets damage three times
		player.takeDamage();
		player.takeDamage();
		player.takeDamage();
		// then the player should have two hearts
		assertTrue((player.getHealth() == 2));
		
	}

	/**
	 * Check if the Player has the specific key to open the door and the player
	 * should be around the door to open the door
	 * @throws InvalidBackpackException 
	 */
	@Test
	public void testCanOpenDoor() throws InvalidBackpackException {
		// Create a Player with specific coordinates
		player = new Player(name, xLocation, yLocation);
		// Create a DoorItem with specific coordinates
		DoorItem i = new DoorItem("map1", 10, true, xLocation/4, yLocation/4 );
		// Create a gun Item with specific coordinates next to the player
		IMap m = new MockMap();
		// Create a gun Item with specific coordinates next to the player
		// player needs to pick up the gun
		m.placeItem(i, 98, 98);
		// Create a KeyID with specific coordinates
		Item second = new Key(10, "yellow");
		// Create a gun Item with specific coordinates next to the player
		m = new MockMap();
		// Create a gun Item with specific coordinates next to the player
		// player needs to pick up the gun
		m.placeItem(second, 102, 102);
		// Use the pickup item in the Player Item to pick up the item
		player.getBackpack().pickUpItem(second);	
		if(player.getBackpack().getInventory().contains(second)){
			assertTrue(player.canOpenDoor(i));
		}
	}
	
	/**
	 * Check if the player can die after getting five times attack
	 */
	@Test
	public void testDeath() {
		// Create a Player with specific coordinates
		player = new Player(name, xLocation, yLocation);
		// attack the player for five times because
		// Initially the player has five hearts 
		for(int i = 0; i<5; i++){
			player.takeDamage();
			if((player.getHealth() == 0)){
				assertTrue((player.getHealth() == 0));
			}
		}
	}


	/**
	 * Check if the player can not walk through the walls and the doors if the
	 * player doesnt have the key.
	 */
	@Test
	public void testCanMove() {

	}
}
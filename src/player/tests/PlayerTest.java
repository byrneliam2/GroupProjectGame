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
 * @author Mohsen Javaher
 *
 */
public class PlayerTest {
	private ArrayList<NPC> npcs;
	private Player p;
	private Map m;
	private int xLocation = 100;
	private int yLocation = 100;
	private double mouseX = 50.50;
	private double mouseY = 50.50;



	/**
	 * Check if the Player is in rangeCircle box then it can pick up the item Other than that the test fails
	 */
	@Test
	public void testRangeCircle() {
		// insert a player with specific coordinate
		// insert an item with specific coordinate next to the player
		// use the pick up item from the player class
		// if the player backpack contains the specific item assertTrue
		// else assertFalse
	}

	/**
	 * Check if the defaultFireRate of the player decreases when the Player picks up a gun.
	 */
	@Test
	public void testDefaultFireRate() {
		// Create a Player with specific coordinates
		// Create a gun Item with specific coordinates next to the player
		// player needs to pick up the gun
		// check if the player backpack contatins the gun
			// if it does contain the gun
				//if the fire rate should have increased
					// assertTrue
				// else
					//assertFalse
	}

	/**
	 * Check if when a player picks up an item should appear in the itemsList OR the BackPack.
	 */
	@Test
	public void testPickedUpItem() {

	}

	/**
	 * Check if when a player removes an item from the BackPack should NOT appear in the itemsList OR the BackPack anymore.
	 */
	@Test
	public void testRemoveItem() {

	}

	/**
	 * Check if when a player uses or Equip an item should appear in the itemsList OR the BackPack.
	 */
	@Test
	public void testEquipItem() {

	}

	/**
	 * Check if when a player uses an item.
	 */
	@Test
	public void testUseItem() {

	}

	/**
	 * Check if when a player takes damage When an enemy has shooted around the playerBox.
	 * The Player has a box which has been implemented by using the Rectangle class. Which is kind
	 * of the hit box representing the location of the player.
	 */
	@Test
	public void testTakeDamage() {

	}

	/**
	 * Check if the Player has the specific key to open the door and the player should be around the door to open the door
	 */
	@Test
	public void testCanOpenDoor() {

	}


	/**
	 * Check if the player can not walk through the walls and the doors if the player doesnt have the key.
	 */
	@Test
	public void testCanMove() {

	}

	/**
	 * Check when the player is entering a door should be on a new map
	 */
	@Test
	public void testEnterDoor() {

	}

}

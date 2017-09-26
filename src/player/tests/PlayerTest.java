package player.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import map.Map;
import npc.NPC;
import player.InvalidPlayerExceptions;
import player.Player;

public class PlayerTest {
	private ArrayList<NPC> npcs;
	private Player p;
	private Map m;
	private int xLocation = 100;
	private int yLocation = 100;
	private double mouseX = 50.50;
	private double mouseY = 50.50;


	@Test
	public void testShooting() {


		Player tempPlayer = new Player("Thomas", xLocation, yLocation);
		npcs = new ArrayList<NPC>();
		Map m = new Map("Map1", null, npcs, null);
		tempPlayer.setMap(m);

		try {

			tempPlayer.shoot(mouseX, mouseY);
		} catch (InvalidPlayerExceptions e) {
			fail("The Function didnt work");
		}
	}

	@Test
	public void testShootingTwoIceAtTheSameTime() {

		Player tempPlayer = new Player("Thomas", xLocation, yLocation);
		try {
			tempPlayer.shoot(mouseX, mouseY);
			tempPlayer.shoot(mouseX, mouseY);
			fail("The Function didnt work");
		} catch (InvalidPlayerExceptions e) {
		}
	}
}

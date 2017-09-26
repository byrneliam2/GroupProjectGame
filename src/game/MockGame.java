package game;

import items.Equipable;
import items.Item;
import items.Usable;
import map.World;
import npc.NPC;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A mock game class to use for testing.
 * 
 * @author Thomas Edwards
 * @author Liam Byrne
 */
public class MockGame implements IGame {

	public MockGame() {
		new World(new HashMap<>());
	}

	/******************* View Methods **********************/

	/**
	 * @return empty string.
	 */
	public String getCurrentMap() {
		return "";
	}

	/**
	 * @return new nullified player
	 */
	public Player getPlayer() {
		return new Player("", 0, 0);
	}

	/**
	 * @return game world
	 */
	public World getWorld() {
		return new World(new HashMap<>());
	}

	/**
	 * @return HashMap of item->Point(x,y)
	 */
	public HashMap<Item, Point> getItems() {
		return new HashMap<>();
	}

	/**
	 * @return List of all npc's, each npc has an x,y location similar to the player.
	 */
	public List<NPC> getNPCs() {
		return new ArrayList<>();
	}

	/**
	 * @return List of all bullets in game.
	 */
	public List<Bullet> getBullets() {
		return Bullet.bulletList;
	}

	public List<Item> getInventory() {
		return new ArrayList<>();
	}

	public List<Equipable> getEquippedItems() {
		return new ArrayList<>();
	}

	public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions {
		return false;
	}

	public void interact() throws InvalidPlayerExceptions {
	}

	@Override
	public void shoot(double x, double y) {

	}

	public void dropItem(Item i) throws InvalidPlayerExceptions {
	}

	public void equipItem(Equipable i) throws InvalidPlayerExceptions {
	}

	public void unequipItem(Equipable i) throws InvalidPlayerExceptions {
	}

	public void useItem(Usable u) throws InvalidPlayerExceptions {
	}

	public void shoot(double direction) throws InvalidPlayerExceptions {
	}

	public void pauseGame() {
	}

	public void unPauseGame() {
	}

	public void saveGame() {
	}
}

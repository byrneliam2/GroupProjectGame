package game;

import items.Equipable;
import items.Item;
import items.Usable;
import map.World;
import npc.NPC;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import utils.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 * A mock game class to use for testing.
 * 
 * @author Thomas Edwards
 * @author Liam Byrne
 */
public class MockGame implements IGame {
	private boolean paused = false;

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

	@Override
	public boolean isOver() {
		return false;
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

	public void movePlayer(Direction dir) throws InvalidPlayerExceptions {

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

	@Override
	public void giveObserver(Observer o) {}

	@Override
	public void set(Object arg) {}

	@Override
	public void newGame() {

	}

	public void pauseGame() {
		paused = true;
	}

	public void unPauseGame() {
		paused = false;
	}

	@Override
	public boolean isPaused() {
		return paused;
	}

	public void saveGame() {
	}
}

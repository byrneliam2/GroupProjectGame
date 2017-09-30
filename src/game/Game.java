package game;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import frames.MainDisplay;
import items.Equipable;
import items.Item;
import items.Usable;
import map.World;
import map.WorldParser;
import npc.NPC;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import utils.Direction;

/**
 * Class to be used by front end for getting all the different entities in the
 * game and controlling them.
 *
 * @author Thomas Edwards
 *
 */
public class Game extends Observable implements IGame, Serializable {

	public static boolean GAME_PAUSED = false;

	private Player player;
	private World world;

	/**
	 * Sets up a new game.
	 */
	public Game() {
		this.player = new Player("Tom", 500, 500);
	}

	@Override
	public void giveObserver(Observer o) {
		this.addObserver(o);
	}

	/**
	 * Start the new game.
	 */
	public void newGame() {
		world = WorldParser.parse("world", this.player);
	}

	/******************* View Methods **********************/

	/**
	 * @return the name of the current map that the player is on.
	 */
	public String getCurrentMap() {
		return player.getMap().getMapName();
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public boolean isOver() {
		return player.isDead();// TODO a win condition once we implement a boss fight.
	}

	/**
	 * @return game world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @return list of items currently located on the map.
	 */
	public List<Item> getItems() {
		return player.getMap().getItems();
	}

	/**
	 * @return List of all npc's, each npc has an x,y centre location similar to the
	 *         player.
	 */
	public List<NPC> getNPCs() {
		return player.getMap().getNPCS();
	}

	/**
	 * @return List of all bullets in game. Each bullet has a point location.
	 */
	public List<Bullet> getBullets() {
		return Bullet.bulletList;
	}

	/**
	 * @return the player's inventory (part of the backpack)
	 */
	public List<Item> getInventory() {
		return this.player.getBackpack().getInventory();
	}

	/**
	 * @return the player's equippedItems (part of the backpack)
	 */
	public List<Equipable> getEquippedItems() {
		return this.player.getBackpack().getEquippedItems();
	}

	/******************* Controller Methods ************************/

	/**
	 * @see player.Player#move(int dx, int dy)
	 */
	public void movePlayer(Direction dir) throws InvalidPlayerExceptions {
		player.move(player.getSpeed() * dir.getX(), player.getSpeed() * dir.getY());
	}

	/**
	 *
	 */
	public void interact() throws InvalidPlayerExceptions {
		player.pickUpItem();
	}

	/**
	 * @see player.Player#removeItem(Item i)
	 */
	public void dropItem(Item i) throws InvalidPlayerExceptions {
		player.removeItem(i);
	}

	/**
	 * @see player.Player#equipItem(Equipable i)
	 */
	public void equipItem(Equipable i) throws InvalidPlayerExceptions {
		player.equipItem(i);
	}

	/**
	 * @see player.Player#unequipItem(Equipable i)
	 */
	public void unequipItem(Equipable i) throws InvalidPlayerExceptions {
		player.unequipItem(i);
	}

	/**
	 * @see player.Player#useItem(Usable u)
	 */
	public void useItem(Usable u) throws InvalidPlayerExceptions {
		player.useItem(u);
	}

	/**
	 * @see player.Player#shoot(double mouseX, double mouseY)
	 */
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		player.shoot(mouseX, mouseY);
	}

	public void pauseGame() {
		GAME_PAUSED = true;
		if (this.player.getMap() != null)
			this.player.getMap().pauseMapNPCs();
	}

	public void unPauseGame() {
		GAME_PAUSED = false;
		this.player.getMap().startMapNPCs();
	}

	@Override
	public boolean isPaused() {
		return GAME_PAUSED;
	}

	/**
	 * Saves this game.Game object as a file...
	 */
	public void saveGame() {

	}

	@Override
	public void set() {
		setChanged();
		notifyObservers(null);
	}
}

package game;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import items.Equipable;
import items.Item;
import items.Usable;
import map.World;
import map.WorldParser;
import npc.NPC;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;

/**
 * Class to be used by front end for getting all the different entities in the
 * game and controlling them.
 *
 * @author Thomas Edwards
 *
 */
public class Game implements IGame {

	public static boolean GAME_PAUSED = false;

	private Player player;
	private World world;

	/**
	 * Sets up a new game.
	 */
	public Game() {
		this.player = new Player("Tom", 500, 500);
		world = WorldParser.parse("world", this.player);
		this.player.setMap(world.getStartingMap());
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

	/**
	 * @return game world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @return HashMap of item->Point(x,y)
	 */
	public HashMap<Item, Point> getItems() {
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
	public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions {
		return player.move(dx, dy);
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
	 * @see player.Player#shoot(double direction)
	 */
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		player.shoot(mouseX, mouseY);
	}

	public void pauseGame() {
		GAME_PAUSED = true;
	}

	public void unPauseGame() {
		GAME_PAUSED = false;
	}

	/**
	 * Saves this game.Game object as a file...
	 */
	public void saveGame() {

	}

}

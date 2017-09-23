import java.util.HashMap;
import java.util.List;

import items.Equipable;
import items.Item;
import items.Usable;
import map.Location;
import map.Map;
import map.WorldParser;
import npc.NPC;
import player.Bullet;
import player.Player;

/**
 * Class to be used by front end for getting all the different entities in the game and controlling them.
 *
 * @author Thomas Edwards
 *
 */
public class Game {

	private Player player;

	/**
	 * @param player
	 *            a player which has been placed on the first map.
	 */
	public Game() {
		Map startingMap = WorldParser.parse("WORLD_NAME").getStartingMap();
		this.player = new Player("Tom", 50, 50, startingMap);
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
	 * @return HashMap of item->location(x,y)
	 */
	public HashMap<Item, Location> getItems() {
		return player.getMap().getItems();
	}

	/**
	 * @return List of all npc's
	 */
	public List<NPC> getNPCs() {
		return player.getMap().getNPCS();
	}

	/**
	 * @return List of all bullets in game.
	 */
	public List<Bullet> getBullets() {
		return Bullet.bulletList;
	}

	/******************* Controller Methods ************************/

	/**
	 * @param dx
	 * @param dy
	 * @return true if the player moved through a door (and thus the map needs to be updated).
	 */
	public boolean movePlayer(int dx, int dy) {
		return false;
	}

	public void pauseGame() {

	}

	public void unPauseGame() {

	}

	/**
	 * Interact/Pickup a close Item
	 */
	public void interact() {
		// pickup/interact
	}

	public void dropItem(Item i) {

	}

	public void equipItem(Equipable i) {

	}

	public void unequipItem(Equipable i) {

	}

	public void useItem(Usable u) {

	}

	public void shoot(double direction) {

	}

	/**
	 * Saves this Game object as a file...
	 */
	public void saveGame() {

	}
}

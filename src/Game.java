import java.util.HashMap;
import java.util.List;

import items.Item;
import map.Location;
import npc.NPC;
import player.Bullet;
import player.Player;

/**
 * Class for getting all the different entities in the game.
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
	public Game(Player player) {
		this.player = player;
	}

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
	 * @return HashMap of item->location
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

}

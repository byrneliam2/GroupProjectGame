package game;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import map.World;
import map.WorldParser;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import save_load.SaveLoad;
import common.utils.Direction;

/**
 * Class to be used by front end for getting all the different entities in the
 * game and controlling them.
 *
 * @author Thomas Edwards
 */
public class Game extends Observable implements IGame, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static boolean GAME_PAUSED = false;

	private Player player;
	private World world;

	@Override
	public void giveObserver(Observer o) {
		this.addObserver(o);
	}

	/**
	 * Start the new game.
	 */
	public void newGame() {
		this.player = new Player("Tom", 500, 500);
		this.world = WorldParser.parse("world", this.player);
	}

	/******************* View Methods **********************/

	@Override
	public String getCurrentMap() {
		return player.getMap().getName();
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public int isOver() {
		if (false)
			return 2; // TODO win condition
		if (player.isDead())
			return 1;
		else
			return 0;

	}

	@Override
	public World getWorld() {
		return world;
	}

	/******************* Controller Methods ************************/

	@Override
	public void movePlayer(Direction dir) throws InvalidPlayerExceptions {
		// if the movement caused a change in maps... notify observers
		if (player.move(player.getSpeed() * dir.getX(), player.getSpeed() * dir.getY())) {
			set(getCurrentMap());
		}
	}

	@Override
	public void interact() throws InvalidPlayerExceptions {
		player.pickUpItem();
	}

	@Override
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		player.shoot(mouseX, mouseY);
	}

	@Override
	public void pauseGame() {
		GAME_PAUSED = true;
		if (this.player.getMap() != null)
			this.player.getMap().pauseMapNPCs();
		set("pause");
	}

	@Override
	public void unPauseGame() {
		GAME_PAUSED = false;
		this.player.getMap().startMapNPCs();
		set(getCurrentMap());
	}

	@Override
	public boolean isPaused() {
		return GAME_PAUSED;
	}

	/**
	 * Saves this game.Game object as a file...
	 */
	public void saveGame(String theFilePath) {
		System.out.println("SaveGame MohsenJavehr"+this);
		SaveLoad saveLoad = new SaveLoad(this, theFilePath);
	}

	@Override
	public void stopGame() {
		GAME_PAUSED = true;
		if (this.player.getMap() != null)
			this.player.getMap().pauseMapNPCs();
		Bullet.bulletList.clear();
		set("stop");
	}

	@Override
	public void set(Object arg) {
		setChanged();
		notifyObservers(arg);
	}
}

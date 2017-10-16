package game;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import common.game.IGame;
import common.player.IPlayer;
import map.World;
import map.WorldParser;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.Direction;
import gfx.ImageLoader;
import gfx.ImageUtilities;
import save_load.SaveLoad;

/**
 * Class to be used by front end for getting all the different entities in the
 * game and controlling them.
 *
 * A Game encapsulates the entire back-end and contains a player and a world.
 *
 * @author Thomas Edwards
 */
public class Game extends Observable implements IGame, Serializable {

	public static BufferedImage heart = ImageUtilities.scale(ImageLoader.image("game", "heart", true), 50, 50);
	public static BufferedImage emptyHeart = ImageUtilities.scale(ImageLoader.image("game", "lost-heart", true), 50,
			50);
	public static final boolean DEV_MODE = false;
	public static boolean GAME_PAUSED = false;

	private IPlayer player;
	private World world;

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
	public IPlayer getPlayer() {
		return this.player;
	}

	private int lastHealth = 0;

	@Override
	public int isOver() {
		//Check Player Health
		if(lastHealth < player.getHealth()) lastHealth = player.getHealth();
		else if(player.getHealth() < lastHealth) {
			lastHealth = player.getHealth();
			set("hurt");
		}

		if (world.getMaps().get("Map16").getNPCs().isEmpty())
			return 2;// win condition
		if (player.isDead())
			return 1;// lose condition
		else
			return 0;

	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public void set(Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	@Override
	public void giveObserver(Observer o) {
		this.addObserver(o);
	}

	/******************* Controller Methods ************************/

	@Override
	public void movePlayer(Direction dir) throws InvalidPlayerExceptions {
		player.setCurrentDir(dir);
		// if the movement caused a change in maps... notify observers
		if (player.move(dir.getX(), dir.getY())) {
			set(getCurrentMap());
		}

	}

	@Override
	public void stop() {
		player.setMoving(false);
	}

	@Override
	public void interact() throws InvalidPlayerExceptions {
		player.pickUpItem();
	}

	@Override
	public void shoot(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		player.shoot(mouseX, mouseY);
		set("shoot");
	}

	@Override
	public void specialAbility(double mouseX, double mouseY) throws InvalidPlayerExceptions {
		player.specialAbility(mouseX, mouseY);
		set("shoot");
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

	@Override
	public void saveGame(String theFilePath) {
		SaveLoad.saveGame(this, theFilePath);
	}

	@Override
	public void loadGame(IPlayer player, World world) {
		this.player = player;
		this.world = world;
	}

	@Override
	public void stopGame() {
		GAME_PAUSED = true;
		if (this.player.getMap() != null)
			this.player.getMap().pauseMapNPCs();
		Bullet.bulletList.clear();
		set("stop");
	}

}

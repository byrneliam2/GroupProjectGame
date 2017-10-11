package game;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import map.World;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.Direction;

import java.util.Observer;

/**
 * Game interface used to link the Game and MockGame (null object.) All methods
 * inserted here are purely representative of the current methods being used by
 * Game across the system.
 */
public interface IGame {

	/**
	 * Give an observer to the model to set as its own.
	 *
	 * @param o
	 *            observer
	 */
	void giveObserver(Observer o);

	/**
	 * Set this component to be changed (for {@link java.util.Observable}) and
	 * notify all observers.
	 *
	 * @param arg
	 *            argument for {@link Observer} update
	 */
	void set(Object arg);

	/**
	 * Get the name of the current map the player is on.
	 *
	 * @return current map name
	 */
	String getCurrentMap();

	/**
	 * Start a new game.
	 */
	void newGame();

	/**
	 * Pause the current game, including all NPCs.
	 */
	void pauseGame();

	/**
	 * Take the game out of paused state, see {@link #pauseGame}.
	 */
	void unPauseGame();

	/**
	 * Determine if the game is currently paused.
	 *
	 * @return is game paused?
	 */
	boolean isPaused();

	/**
	 * Get the {@link World} that represents the collection of Maps.
	 *
	 * @return current World
	 */
	World getWorld();

	/**
	 * Move the player in a given direction.
	 *
	 * @param dir
	 *            direction to move in, see {@link Direction}
	 * @throws InvalidPlayerExceptions
	 */
	void movePlayer(Direction dir) throws InvalidPlayerExceptions;

	/**
	 * Pick up an item off the ground.
	 *
	 * @throws InvalidPlayerExceptions
	 */
	void interact() throws InvalidPlayerExceptions;

	/**
	 * Shoot a bullet.
	 *
	 * @param x
	 *            mouse position x
	 * @param y
	 *            mouse position y
	 * @throws InvalidPlayerExceptions
	 */
	void shoot(double x, double y) throws InvalidPlayerExceptions;

	/**
	 * Get the current Player.
	 *
	 * @return player
	 */
	Player getPlayer();

	/**
	 * Determine if the game is over.
	 *
	 * @return 0 for false, 1 for lost, 2 for won
	 */
	int isOver();

	/**
	 * Save the current game.
	 */
	void saveGame(String filePath);

	/**
	 * Load the current game.
	 */
	Game loadGame(String filePath);

	/**
	 * Stop the game entirely.
	 */
	void stopGame();
}

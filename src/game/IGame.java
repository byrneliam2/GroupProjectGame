package game;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import map.World;
import player.InvalidPlayerExceptions;
import player.Player;
import utils.Direction;

/**
 * Game interface used to link the Game and MockGame(null object.)
 * All methods inserted here are purely representative of the current
 * methods being used by Game across the system.
 */
public interface IGame {

    void startGame();

    void pauseGame();

    void unPauseGame();

    boolean isPaused();

    World getWorld();

    void movePlayer(Direction dir) throws InvalidPlayerExceptions;

    void interact() throws InvalidPlayerExceptions;

    void shoot(double x, double y) throws InvalidPlayerExceptions;

    Player getPlayer();

    boolean isOver();
}

package game;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import map.World;
import player.InvalidPlayerExceptions;
import player.Player;

/**
 * Game interface used for testing purposes.
 */
public interface IGame {

    void pauseGame();

    void unPauseGame();

    World getWorld();

    boolean movePlayer(int i, int i1) throws InvalidPlayerExceptions;

    void interact() throws InvalidPlayerExceptions;

    void shoot(double x, double y) throws InvalidPlayerExceptions;

    Player getPlayer();
}

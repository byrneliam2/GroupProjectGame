package game;

import common.game.IGame;
import map.World;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.Direction;

import java.util.HashMap;
import java.util.Observer;

/**
 * A mock game class to use for testing. The class only contains methods inherited
 * from the Game interface and any others used for testing purposes.
 *
 * @author Thomas Edwards
 * @author Liam Byrne
 */
public class MockGame implements IGame {

    private boolean paused = false;

    public MockGame() {
        new World(new HashMap<>());
    }

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
    public int isOver() {
        return 0;
    }

    @Override
    public World getWorld() {
        return new World(new HashMap<>());
    }

    @Override
    public void movePlayer(Direction dir) throws InvalidPlayerExceptions {}

    @Override
    public void interact() throws InvalidPlayerExceptions {}

    @Override
    public void shoot(double x, double y) {}

    @Override
    public void giveObserver(Observer o) {}

    @Override
    public void set(Object arg) {}

    @Override
    public void newGame() {}

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

    @Override
    public void saveGame(String filePath) {}

    @Override
    public void stopGame() {}
}

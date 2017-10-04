package game;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import items.Item;
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
 */
public class Game extends Observable implements IGame, Serializable {

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
    public boolean isLost() {
        return player.isDead();// TODO a win condition once we implement a boss fight.
    }

    @Override
    public World getWorld() {
        return world;
    }

    /******************* Controller Methods ************************/

    @Override
    public void movePlayer(Direction dir) throws InvalidPlayerExceptions {
        //if the movement caused a change in maps... notify observers
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
    public void saveGame() {
        //
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

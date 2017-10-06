package controller;

import controller.common.Command;
import controller.common.IController;
import game.IGame;
import player.InvalidPlayerExceptions;
import common.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.HashSet;
import java.util.Set;

public class Controller implements IController {
    private IGame game;
    private Set<Command> currentCommands;
    private KeyListener keyboard;
    private MouseListener mouse;

    public Controller(IGame game) {
        this.game = game;
        this.currentCommands = new HashSet<>();

        //Setup Listeners
        keyboard = new KeyListener(this);
        mouse = new MouseListener(this);
    }

    /**
     * Both children Objects, Keyboard and Mouse, will notify their parent (this) if a command is pressed.
     * This will then be added to a set of all the current keys being pressed, so that when 'update()' is called
     * the Controller will know which commands to invoke.
     * @param cmd The command to logged
     * @param pressed If the command is being pressed, or if released
     */
    public void notifyCommands(Command cmd, boolean pressed) {
        if (pressed)
            currentCommands.add(cmd);
        else
            currentCommands.remove(cmd);
    }

    /**
     * This method is to be called during a game update, this will instruct IGame object on how to control the player.
     * @param cmd The command to be invoked.
     */
    private void processCommand(Command cmd) {
        try {
            if (!game.isPaused()) {
                switch (cmd) {
                    case MOVE_UP:
                        game.movePlayer(Direction.N);
                        break;
                    case MOVE_LEFT:
                        game.movePlayer(Direction.W);
                        break;
                    case MOVE_DOWN:
                        game.movePlayer(Direction.S);
                        break;
                    case MOVE_RIGHT:
                        game.movePlayer(Direction.E);
                        break;
                    case INTERACT:
                        game.interact();
                        break;
                    case PRIMARY_ATTACK:
                        game.shoot(mouse.getX(), mouse.getY());
                        break;
                    case SECONDARY_ATTACK:
                        //game.shoot(mouse.getX(), mouse.getY()); TODO: After Secondary File is Implemented
                        break;
                    case PAUSE:
                        game.pauseGame();
                        break;
                }
            }
        } catch (InvalidPlayerExceptions ignored) { }
    }

    @Override
    public void update() {
        if (!game.isPaused())
            currentCommands.forEach(this::processCommand);
    }

    @Override
    public void reloadController() {
        currentCommands.clear();
    }

    @Override
    public KeyAdapter getKeyAdapter() {
        return keyboard;
    }

    @Override
    public MouseAdapter getMouseAdapter() {
        return mouse;
    }
}

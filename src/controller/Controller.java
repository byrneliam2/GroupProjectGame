package controller;

import controller.enums.Command;
import game.IGame;
import player.InvalidPlayerExceptions;
import utils.Direction;

import java.util.HashSet;
import java.util.Set;

public class Controller implements IController {
    private IGame game;
    private Set<Command> currentCommands;
    private MouseListener mouse;

    public Controller(IGame game, KeyListener keyboard, MouseListener mouse) {
        this.game = game;
        this.mouse = mouse;
        this.currentCommands = new HashSet<>();

        keyboard.setController(this);
        mouse.setController(this);
    }

    /**
     * Both children Objects, Keyboard and Mouse, will notify their parent (this) if a command is pressed.
     * This will then be added to a set of all the current keys being pressed, so that when 'update()' is called
     * the Controller will know which commands to invoke.
     * @param cmd The command to logged
     * @param pressed If the command is being pressed, or if released
     */
    void notifyCommands(Command cmd, boolean pressed) {
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
                    case SHOOT:
                        game.shoot(mouse.getX(), mouse.getY());
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
}

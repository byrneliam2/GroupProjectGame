package controller;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: Mohsen Javaher
 */

import common.controller.Command;
import common.controller.IController;
import common.game.IGame;
import common.utils.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static common.controller.Command.*;

/**
 * The Controller Class handles all user input for the game, either from the Keyboard or the Mouse.
 * This is used to allow the player to move / interact with the World.
 */
public class Controller implements IController {
    private IGame game;
    private Set<Command> commands;
    private Stack<Command> movements;
    private KeyListener keyboard;
    private MouseListener mouse;

    public Controller(IGame game) {
        this.game = game;
        this.commands = new HashSet<>();
        this.movements = new Stack<>();

        // Setup Listeners
        keyboard = new KeyListener(this);
        mouse = new MouseListener(this);
    }

    /**
     * Both children Objects, Keyboard and Mouse, will notify their parent (this) if a command is pressed. This will then be added to a set of all the current
     * keys being pressed, so that when 'update()' is called the Controller will
     * know which commands to invoke.
     *
     * @param cmd The command to logged
     * @param pressed If the command is being pressed, or if released
     */
    public void notifyCommands(Command cmd, boolean pressed) {
        if (pressed) {
            if (!commands.contains(cmd) && isMovement(cmd)) movements.push(cmd);
            commands.add(cmd);
        } else {
            if(commands.contains(cmd)) movements.remove(cmd);
            commands.remove(cmd);
        }
    }

    /**
     * This method handles the Player Movement, by only moving the player in the direction of their most
     * recent key-press. This is done by using a Stack stored in the fields storing the most recent key-presses in
     * order, then when the method is called the most recent key-press is peeked at and performed.
     */
    private void handleMovement() {
        if (movements.isEmpty()) return;
        try {
            switch (movements.peek()) {
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
            }
        } catch (Exception ignored) { }
    }

    /**
     * This method handles all non-movement commands the user may perform, Such as interacting with objects
     * and shooting. This is performed by iterating over all of the current pressed keys in a Set of Commands
     * stored in a field
     */
    private void handleCommands() {
        for (Command c : commands) {
            try {
                if (!game.isPaused()) {
                    switch (c) {
                        case INTERACT:
                            game.interact();
                            break;
                        case PRIMARY_ATTACK:
                            game.shoot(mouse.getX(), mouse.getY());
                            break;
                        case SECONDARY_ATTACK:
                            game.specialAbility(mouse.getX(), mouse.getY());
                            break;
                        case PAUSE:
                            game.pauseGame();
                            break;
                    }
                }
            } catch (Exception ignored) { }
        }
    }

    @Override
    public void update() {
        if (game.isPaused()) return;

        //Handle the commands.
        handleMovement();
        handleCommands();

        //Alert the game to stop animating the players movement.
        if (commands.stream().noneMatch(this::isMovement)) game.stop();
    }

    @Override
    public void reloadController() {
        commands.clear();
        movements.clear();
    }

    @Override
    public KeyAdapter getKeyAdapter() {
        return keyboard;
    }

    @Override
    public MouseAdapter getMouseAdapter() {
        return mouse;
    }

    /**
     * Detects if the provided command is a Movement-Command.
     * @param cmd The command to check
     * @return If cmd == movement command
     */
    private boolean isMovement(Command cmd) {
        return Arrays.asList(MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT).contains(cmd);
    }
}

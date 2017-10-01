package controller;

import controller.enums.Command;
import frames.MainDisplay;
import game.IGame;
import player.InvalidPlayerExceptions;
import utils.Direction;

import javax.swing.*;
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

        new Timer(MainDisplay.FRAMERATE, (e) -> update()).start();
    }

    void notifyCommands(Command cmd, boolean pressed) {
        if (pressed)
            currentCommands.add(cmd);
        else
            currentCommands.remove(cmd);
    }

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

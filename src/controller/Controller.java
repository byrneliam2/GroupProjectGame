package controller;

import frames.MainDisplay;
import game.IGame;
import player.InvalidPlayerExceptions;
import utils.Direction;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static controller.Controller.KeyboardCommands.*;

/**
 * The Main Class for the Controller Library, contains all logic for the user to control
 * their player.
 */
public class Controller extends KeyAdapter {
	private IGame model;
	private MousePosition mouse;
	private Set<Integer> pressed;

	public Controller(IGame model, MousePosition mouse) {
		this.model = model;
		this.mouse = mouse;
		this.pressed = new HashSet<>();

        new Timer(MainDisplay.FRAMERATE, (e)-> update()).start();
    }

	/**
	 * Whenever a user pressed a key, the event will be sent off for processing
	 */
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		this.pressed.add(e.getKeyCode());
	}

	/**
	 * Whenever a user releases a key, the event will be sent off for processing
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		this.pressed.remove(e.getKeyCode());
	}

	public void update() {
		for (Integer i : pressed) {
			processInput(i);
		}
	}

    /**
     * When given an input, this method will update the 'MODEL' with the users input.
     *
     * @param keybind the key the user pressed
     * @return true if the input succeeded, false if it didn't exist or failed.
     */
    public boolean processInput(int keybind) {
        try {
            //All Movement Commands (Can loop)
            if (keybind == KEY_UP.getKeybind()) model.movePlayer(Direction.N);
            else if (keybind == KEY_DOWN.getKeybind()) model.movePlayer(Direction.S);
            else if (keybind == KEY_LEFT.getKeybind()) model.movePlayer(Direction.W);
            else if (keybind == KEY_RIGHT.getKeybind()) model.movePlayer(Direction.E);
            else if (keybind == KEY_USE.getKeybind()) {
                model.interact();
                return true;
            }else  if (keybind == KEY_ATTACK.getKeybind()) {
                model.shoot(mouse.getX(), mouse.getY());
                return true;
            }else  if (keybind == KEY_MENU.getKeybind()) {
                model.pauseGame();
                return true;
            }
        } catch (InvalidPlayerExceptions e) {
            //System.out.println(e.getMessage());
        }
        return true;
    }

	/**
	 * Holds all Keybinds for the game, the user can change each keybind using the 'setKeybind()' method.
	 */
	public enum KeyboardCommands {
		// All Keyboard inputs required by the game
		KEY_UP(KeyEvent.VK_UP), KEY_DOWN(KeyEvent.VK_DOWN), KEY_LEFT(KeyEvent.VK_LEFT), KEY_RIGHT(
				KeyEvent.VK_RIGHT), KEY_USE(KeyEvent.VK_F), KEY_ATTACK(KeyEvent.VK_SPACE), KEY_MENU(KeyEvent.VK_ESCAPE);

		private int original;
		private int current;

		KeyboardCommands(int keybind) {
			this.original = keybind;
			this.current = keybind;
		}

		public int getKeybind() {
			return this.current;
		}

		public void setKeybind(int keybind) {
			this.current = keybind;
		}

		public void resetKeybind() {
			this.current = original;
		}
	}

}

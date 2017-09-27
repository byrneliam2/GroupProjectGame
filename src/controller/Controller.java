package controller;

import game.IGame;
import player.InvalidPlayerExceptions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static controller.Controller.KeyboardCommands.*;


public class Controller extends KeyAdapter {
    private IGame model;
    private MousePosition mouse;

    public Controller(IGame model, MousePosition mouse) {
        this.model = model;
        this.mouse = mouse;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        processInput(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        processInput(e.getKeyCode(), false);
    }

    /**
     * When given an input, this method will update the 'MODEL' with the users input.
     * @param keybind the key the user pressed
     * @param state if the user pressed (true) or released (false) the key
     * @return true if the input succeeded, false if it didn't exist or failed.
     */
    public boolean processInput(int keybind, boolean state){
        System.out.println(keybind);
        try {
            //TODO: ALL MOVEMENT KEYS ARE INCORRECT (NEED STATE AT THE END WHEN DISCUSSED)
            if (keybind == KEY_UP.getKeybind()) {
                return model.movePlayer(0, -10);
            }
            else if (keybind == KEY_DOWN.getKeybind()) {
                return model.movePlayer(0, 10);
            }
            else if (keybind == KEY_LEFT.getKeybind()) {
                return model.movePlayer(-10, 0);
            }
            else if (keybind == KEY_RIGHT.getKeybind()) {
                return model.movePlayer(10, 0);
            }
            //TODO: THIS SHOULD BE THE ONLY KEY ALL INTERACTIONS
            else if (keybind == KEY_USE.getKeybind()){
                model.interact();
                return true;
            }
            else if (keybind == KEY_ATTACK.getKeybind()) {
                model.shoot(mouse.getX(), mouse.getY());
                return true;
            }
            else if (keybind == KEY_MENU.getKeybind()) {
                model.pauseGame();
                return true;
            }
        } catch (InvalidPlayerExceptions ignored) {}

        return false;
    }

    public enum KeyboardCommands {
        KEY_UP(KeyEvent.VK_UP),
        KEY_DOWN(KeyEvent.VK_DOWN),
        KEY_LEFT(KeyEvent.VK_LEFT),
        KEY_RIGHT(KeyEvent.VK_RIGHT),
        KEY_USE(KeyEvent.VK_F),
        KEY_ATTACK(KeyEvent.VK_SPACE),
        KEY_MENU(KeyEvent.VK_ESCAPE);

        private int keybind;

        KeyboardCommands(int keybind) {
            this.keybind = keybind;
        }

        public int getKeybind() {
            return this.keybind;
        }

        public void setKeybind(int keybind) {
            this.keybind = keybind;
        }
    }


}

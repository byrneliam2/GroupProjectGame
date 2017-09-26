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

    private void processInput(int keybind, boolean state){
        try {
            //TODO: ALL MOVEMENT KEYS ARE INCORRECT
            if (keybind == KEY_UP.getKeybind()) {
                //model.movePlayer(0, -1, state);
            }
            else if (keybind == KEY_DOWN.getKeybind()) {
                //model.movePlayer(0, 1, state);
            }
            else if (keybind == KEY_LEFT.getKeybind()) {
                //model.movePlayer(-1, 0, state);
            }
            else if (keybind == KEY_RIGHT.getKeybind()) {
                //model.movePlayer(1, 0, state);
            }
            //TODO: THIS SHOULD BE THE ONLY KEY ALL INTERACTIONS
            else if (keybind == KEY_USE.getKeybind()){
                model.interact();
            }
            else if (keybind == KEY_ATTACK.getKeybind()) {
                model.shoot(mouse.getX(), mouse.getY());
            }
            else if (keybind == KEY_MENU.getKeybind()) {
                model.pauseGame();
            }
        } catch (InvalidPlayerExceptions e) {
            e.printStackTrace();
        }
    }

    enum KeyboardCommands {
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

package controller;

import game.Game;
import player.InvalidPlayerExceptions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static controller.KeyboardController.KeyboardCommands.*;


public class KeyboardController extends KeyAdapter {
    private Game model;
    private MouseController mouse;

    public KeyboardController(Game model, MouseController mouse) {
        this.model = model;
        this.mouse = mouse;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        try {
            int keybind = e.getKeyCode();

            //TODO: ALL MOVEMENT KEYS ARE INCORRECT
            if (keybind == KEY_UP.getKeybind()) model.movePlayer(0,-1);
            if (keybind == KEY_DOWN.getKeybind()) model.movePlayer(0,1);
            if (keybind == KEY_LEFT.getKeybind()) model.movePlayer(-1,0);
            if (keybind == KEY_RIGHT.getKeybind()) model.movePlayer(1,0);

            if (keybind == KEY_USE.getKeybind()) model.interact();
            if (keybind == KEY_ATTACK.getKeybind()) model.shoot(calculateAngle());
            if (keybind == KEY_MENU.getKeybind()) model.pauseGame();
        } catch (InvalidPlayerExceptions ie) {
            ie.printStackTrace();
        }
    }

    private double calculateAngle() {
        double pX = model.getPlayer().getxLocation();
        double pY = model.getPlayer().getyLocation();

        double mX = mouse.getCurrentPosition().getX();
        double mY = mouse.getCurrentPosition().getY();

        double x = mX - pX;
        double y = mY - pY;


        // math to calculate 360 degree angle.
        // might have to catch divide-by-0 errors. TEST those!
        double angle;
        if (x > 0 && y >= 0) {
            angle = 3 * Math.PI / 2 + Math.atan(y / x);
        } else if (x >= 0 && y < 0) {
            angle = Math.PI - Math.atan(x / y);
        } else if (x < 0 && y <= 0) {
            angle = Math.PI / 2 + Math.atan(y / x);
        } else {
            angle = -Math.atan(x / y);
        }

        return angle;
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

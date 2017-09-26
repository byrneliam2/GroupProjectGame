package controller;

import game.Game;
import player.InvalidPlayerExceptions;
import utils.MathUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static controller.Controller.KeyboardCommands.*;


public class Controller extends KeyAdapter {
    private Game model;
    private MousePosition mouse;

    public Controller(Game model, MousePosition mouse) {
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
            if (keybind == KEY_ATTACK.getKeybind()) model.shoot(mouse.getCurrentPosition().getX(),mouse.getCurrentPosition().getY());
            if (keybind == KEY_MENU.getKeybind()) model.pauseGame();
        } catch (InvalidPlayerExceptions ie) {
            ie.printStackTrace();
        }
    }

    private double getAngle() {
        double playerX = model.getPlayer().getxLocation();
        double playerY = model.getPlayer().getyLocation();

        double mouseX = mouse.getCurrentPosition().getX();
        double mouseY = mouse.getCurrentPosition().getY();

        return MathUtils.calculateAngle(mouseX, mouseY, playerX, playerY);
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

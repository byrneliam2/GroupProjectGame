package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

    }

    public enum KeyboardCommands {
        KEY_UP(KeyEvent.VK_UP),
        KEY_DOWN(KeyEvent.VK_DOWN),
        KEY_LEFT(KeyEvent.VK_LEFT),
        KEY_RIGHT(KeyEvent.VK_RIGHT),
        KEY_USE(KeyEvent.VK_F),
        KEY_ATTACK(KeyEvent.VK_SPACE);

        private int keybind;

        KeyboardCommands(int keybind) {
            this.keybind = keybind;
        }

        public int getKeybind() {
            return keybind;
        }

        public void setKeybind(int keybind) {
            this.keybind = keybind;
        }
    }
}

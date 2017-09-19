package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static controller.KeyboardController.KeyboardCommands.*;

public class KeyboardController extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        toggleState(e.getKeyCode(), true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        toggleState(e.getKeyCode(), false);
    }


    private void toggleState(int keybind, boolean state){
        if(keybind == KEY_UP.getKeybind());
        else if(keybind == KEY_DOWN.getKeybind());
        else if(keybind == KEY_LEFT.getKeybind());
        else if(keybind == KEY_RIGHT.getKeybind());
        else if(keybind == KEY_USE.getKeybind());
        else if(keybind == KEY_ATTACK.getKeybind());
    }

     enum KeyboardCommands {
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
            return this.keybind;
        }

        public void setKeybind(int keybind) {
            this.keybind = keybind;
        }
    }
}

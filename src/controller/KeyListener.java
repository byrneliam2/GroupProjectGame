package controller;

import controller.enums.InputType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter implements IListener{
    private Controller parent;

    public KeyListener(Controller parent) {
        this.parent = parent;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        notifyController(e.getKeyCode(), InputType.KEYBOARD, parent,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notifyController(e.getKeyCode(), InputType.KEYBOARD, parent,false);
    }
}

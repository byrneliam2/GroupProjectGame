package controller;

import controller.enums.InputType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter implements IListener{
    private Controller parent;

    @Override
    public void keyPressed(KeyEvent e) {
        notifyController(e.getKeyCode(), InputType.KEYBOARD, parent,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notifyController(e.getKeyCode(), InputType.KEYBOARD, parent,false);
    }

    /**
     * Add the Controller as a parent for notifications in the future.
     * @param parent The Controller
     */
    void setController(Controller parent){
        this.parent = parent;
    }
}

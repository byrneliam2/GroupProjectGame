package controller;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: Mohsen Javaher
 */

import common.controller.Command;
import common.controller.InputType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter  {
    private Controller parent;

    public KeyListener(Controller parent) {
        this.parent = parent;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        notifyController(e.getKeyCode(), parent,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notifyController(e.getKeyCode(), parent,false);
    }

    /**
     * Notifies the Controller of an Event Triggering.
     * @param input The key with an event change
     * @param pressed If the key was Clicked or Released.
     */
    private void notifyController(Integer input, Controller parent, boolean pressed){
        for(Command cmd : Command.values()) {
            if(!cmd.getType().equals(InputType.KEYBOARD)) continue;
            else if(!cmd.getValue().equals(input)) continue;
            parent.notifyCommands(cmd, pressed);
        }
    }
}

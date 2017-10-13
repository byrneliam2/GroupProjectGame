package controller;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: Mohsen Javaher
 */

import common.controller.Command;
import common.controller.InputType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private Controller parent;
    private int mouseX;
    private int mouseY;

    public MouseListener(Controller parent) {
        this.parent = parent;
        this.mouseX = 0;
        this.mouseY = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        notifyController(e.getButton(), parent,true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        notifyController(e.getButton(), parent,false);
    }

    /* Listeners Required for Shooting Direction */

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    /**
     * Notifies the Controller of an Event Triggering.
     * @param input The key with an event change
     * @param pressed If the key was Clicked or Released.
     */
    private void notifyController(Integer input, Controller parent, boolean pressed){
        for(Command cmd : Command.values()) {
            if(!cmd.getType().equals(InputType.MOUSE)) continue;
            else if(!cmd.getValue().equals(input)) continue;
            parent.notifyCommands(cmd, pressed);
        }
    }


    /**
     * @return The Current X coordinate of the mouse on the screen
     */
    public double getX(){
        return this.mouseX;
    }

    /**
     * @return The Current Y coordinate of the mouse on the screen
     */
    public double getY(){
        return this.mouseY;
    }
}

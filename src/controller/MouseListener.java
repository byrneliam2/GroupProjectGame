package controller;

import controller.enums.Command;
import controller.enums.InputType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private Controller parent;
    private int mouseX;
    private int mouseY;

    public MouseListener() {
        this.mouseX = 0;
        this.mouseY = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        notifyController(e.getButton(), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        notifyController(e.getButton(), false);
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

    private void notifyController(Integer input, boolean pressed){
        for(Command cmd : Command.values()) {
            if(!cmd.getType().equals(InputType.MOUSE)) continue;
            else if(!cmd.getValue().equals(input)) continue;
            parent.notifyCommands(cmd, pressed);
        }
    }

    void setController(Controller parent){
        this.parent = parent;
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

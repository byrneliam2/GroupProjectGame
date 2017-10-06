package controller;

import controller.enums.InputType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter implements IListener{
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
        notifyController(e.getButton(), InputType.MOUSE, parent,true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        notifyController(e.getButton(), InputType.MOUSE, parent,false);
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

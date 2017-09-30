package controller;

import controller.enums.Command;
import controller.enums.InputType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private Controller parent;
    private Point currentPosition;

    public MouseListener() {
        this.currentPosition = new Point(0,0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        notifyController(e.getButton(), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        notifyController(e.getButton(), false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.currentPosition.setLocation(e.getX(),e.getY());
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
        return currentPosition.getX();
    }

    /**
     * @return The Current Y coordinate of the mouse on the screen
     */
    public double getY(){
        return currentPosition.getY();
    }
}

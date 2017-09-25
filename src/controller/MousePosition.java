package controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Used by the Game to detect the current mouse position on the screen at any given time.
 */
public class MousePosition extends MouseAdapter {
    private Point currentPosition;

    public MousePosition() {
        this.currentPosition = new Point(0,0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        this.currentPosition.setLocation(e.getX(),e.getY());
    }

    /**
     * @return the x and y coordinates of the mouse
     */
    Point getCurrentPosition() {
        return currentPosition.getLocation();
    }
}

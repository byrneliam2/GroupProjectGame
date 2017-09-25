package controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public Point getCurrentPosition() {
        return currentPosition.getLocation();
    }
}

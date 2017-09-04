package frame;

/*
 * Liam: 300338518
 * 4/09/2017
 * SWEN222PROJECT
 */

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The MainFrame represents the master frame of the application...
 */
public class MainFrame extends JFrame implements Observer {

    private static final int F_WIDTH = 1280;
    private static final int F_HEIGHT = 720;

    public MainFrame() {
        super("Game");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(F_WIDTH, F_HEIGHT));
        this.setFocusable(true);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

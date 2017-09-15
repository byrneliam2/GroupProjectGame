package frames;

/*
 * Liam: 300338518
 * 4/09/2017
 * SWEN222PROJECT
 */

import frames.cards.ScreenCard;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * The MainFrame represents the master frame of the application. It
 */
public class MainFrame extends JFrame implements Observer {

    /* Attributes */
    private Collection<ScreenCard> cards;

    /* Constants */
    private static final int F_WIDTH = 1280;
    private static final int F_HEIGHT = 720;
    private static final String GAME_TITLE = "The Fallacy of the Prophecy";

    public MainFrame() {
        super(GAME_TITLE);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(F_WIDTH, F_HEIGHT));
        this.setLayout(new CardLayout());
        this.setFocusable(true);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

package frames;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The MainFrame represents the master frame of the application. It
 */
public class MainFrame extends JFrame implements Observer {

    /* Attributes */
    private Map<String, Card> cards;

    /* Constants */
    private static final int F_WIDTH = 1280;
    private static final int F_HEIGHT = 720;
    private static final String GAME_TITLE = "The Fallacy of the Prophecy";

    public MainFrame() {
        super(GAME_TITLE);

        cards = new HashMap<>();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(F_WIDTH, F_HEIGHT));
        this.setLayout(new CardLayout());
        this.setFocusable(true);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Add a card (new screen) to the main frame.
     */
    public void addScreen() {
        //
    }

    /**
     * Change the current screen that is being displayed.
     */
    public void switchScreen() {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, "");
    }

    @Override
    public void update(Observable o, Object arg) {
        // redraw
    }
}

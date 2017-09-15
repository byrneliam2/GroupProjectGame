package frames;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.cards.Card;
import frames.cards.*;

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

        doSetup();
        this.pack();
        this.setVisible(true);
    }

    private void doSetup() {
        // add fixed cards
        cards.put("menu",  new MenuCard());
        cards.put("pause", new PauseCard());
        // get model details and construct enough map cards to fit
        for (int i = 0; i < 9; i++) { // replace 9 with model value
            cards.put("level" + i, new MapCard());
        }
    }

    /**
     * Add a card (new screen) to the main frame.
     */
    private void addScreen() {
        //
    }

    /**
     * Change the current screen that is being displayed.
     */
    private void switchScreen() {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, "");
    }

    @Override
    public void update(Observable o, Object arg) {
        // redraw
        // switch screen if need be (use arg)
    }
}

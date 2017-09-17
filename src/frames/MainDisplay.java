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
 * The MainDisplay represents the primary UI component for the front end
 * of the game. The class holds the master frame (which it adds itself to)
 * and a collection of Cards. Each Card represents a screen of the game
 * (see {@link Card} class for more information.)
 */
public class MainDisplay extends JComponent implements Observer {

    /* Swing attributes */
    private JFrame master;

    /* Other attributes */
    private Map<String, Card> cards;
    private Card currentCard;

    /* Constants */
    public static final int F_WIDTH = 1280;
    public static final int F_HEIGHT = 720;

    public MainDisplay() {
        master = new JFrame();
        cards = new HashMap<>();

        // master frame setup
        master.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        master.setResizable(false);
        master.setUndecorated(true);
        master.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // this component setup
        this.setLayout(new CardLayout());
        this.doSetup();

        master.pack();
        master.setVisible(true);

        redraw();
    }

    /**
     * Perform first time setup for the MainDisplay.
     */
    private void doSetup() {
        // add fixed cards
        cards.put("menu",  new MenuCard());
        cards.put("pause", new PauseCard());
        // get model details and construct enough map cards to fit
        for (int i = 0; i < 9; i++) { // replace 9 with model value
            cards.put("level" + i, new MapCard());
            // set up each level
            MapCard m = (MapCard) cards.get("level" + i);
            //
        }

        // add all
        this.add(cards.get("menu"), "menu");
        this.add(cards.get("pause"), "pause");
        for (int i = 0; i < 9; i++) this.add(cards.get("level" + i), "level" + i);
        master.add(this);

        // finally, make the menu screen visible
        switchScreen("menu");
        //switchScreen("pause");
    }

    /**
     * Change the current screen that is being displayed.
     * @param key name of panel to switch to
     */
    private void switchScreen(String key) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, key);
        currentCard = cards.get(key);
    }

    /**
     * Redraw the display by calling redraw on the current card and
     * updating the master frame. We don't need to update this component
     * since the frame's redraw should call it anyway.
     */
    private void redraw() {
        // redraw and repaint the current screen
        currentCard.redraw();
        currentCard.update();
        // call the frame's redraw methods
        master.revalidate();
        master.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        // switch screen if need be (use arg)
        redraw();
    }
}

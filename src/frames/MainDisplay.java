package frames;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import audio.AudioHandler;
import controller.KeyboardController;
import controller.MouseController;
import frames.cards.Card;
import frames.cards.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;
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
    private Timer timer;

    /* Other attributes */
    private Map<String, Card> cards;
    private Card currentCard;
    private AudioHandler audioHandler;

    public MainDisplay() {
        master = new JFrame();
        cards = new HashMap<>();
        audioHandler = new AudioHandler();

        // master frame setup
        master.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        master.setResizable(false);
        master.setUndecorated(true);
        master.setExtendedState(JFrame.MAXIMIZED_BOTH);
        master.addKeyListener(new KeyboardController());
        master.addMouseListener(new MouseController());

        // this component setup
        this.setLayout(new CardLayout());
        this.doSetup();

        master.pack();
        master.setVisible(true);
    }

    /**
     * Perform first time setup for the MainDisplay.
     */
    private void doSetup() {
        // set UI properties first
        doUISetup();

        // add fixed cards
        cards.put("menu",  new MenuCard());
        cards.put("pause", new PauseCard());
        cards.put("settings", new SettingsCard());

        // get model details and construct enough map cards to fit
        for (int i = 0; i < 9; i++) { // TODO replace 9 with model value
            cards.put("level" + i, new MapCard());
            // set up each level
            MapCard m = (MapCard) cards.get("level" + i);
            // TODO add map setup
        }

        // setup action listeners in each card
        // needs a reference to this class to assign correctly
        for (Card c : cards.values()) c.setComponentActions(this);

        // add all
        this.add(cards.get("menu"), "menu");
        this.add(cards.get("pause"), "pause");
        this.add(cards.get("settings"), "settings");
        for (int i = 0; i < 9; i++) this.add(cards.get("level" + i), "level" + i);
        master.add(this);

        // finally, make the menu screen visible
        switchScreen("menu");
        //audioHandler.queueMusic(null);
    }

    private void doUISetup() {
        // set the look of option panes
        // https://stackoverflow.com/questions/1951558/list-of-java-swing-ui-properties
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", Color.BLACK);
    }

    /**
     * Change the current screen that is being displayed.
     * @param key name of panel to switch to
     */
    private void switchScreen(String key) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, key);
        currentCard = cards.get(key);
        // force a redraw on the new card
        redraw();
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
        if (arg instanceof String)
            switchScreen((String) arg);
        redraw();
    }
}

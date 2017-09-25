package frames;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import audio.AudioHandler;
import controller.Controller;
import controller.MousePosition;
import frames.cards.Card;
import frames.cards.*;
import game.Game;
import map.World;

import javax.swing.*;
import javax.swing.Timer;
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

    /* Other UI attributes */
    private Map<String, Card> cards;
    private Card currentCard, lastCard;
    private AudioHandler audioHandler;
    private Controller keyboard;

    /* game.Game attributes */
    private Game game;

    public MainDisplay() {
        game = new Game();
        game.pauseGame();

        master = new JFrame();
        currentCard = null;
        cards = new LinkedHashMap<>();
        audioHandler = new AudioHandler();

        // master frame setup
        master.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        master.setResizable(false);
        master.setUndecorated(true);
        master.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // controller setup
        MousePosition mouse = new MousePosition();
        keyboard = new Controller(game, mouse);
        master.addKeyListener(keyboard);
        master.addMouseMotionListener(mouse);

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

        /* ******************************************************************************
         * Note that cards have named references in three places:
         * - in the map that keeps a record of all cards created
         * - in the CardLayout that keeps a record of all cards added to it
         * - inside of each Card (a Card knows the name it has been given in this class
         *   for both the mapping and the layout manager)
         ***************************************************************************** */

        // set UI properties first
        //doUISetup();

        // add fixed cards
        cards.put("menu",  new MenuCard("menu"));
        cards.put("pause", new PauseCard("pause"));
        cards.put("settings", new SettingsCard("settings"));

        // get model details and construct enough map cards to fit
        for (Map.Entry m : World.maps.entrySet()) {
            String name = (String) m.getKey();
            map.Map map = (map.Map) m.getValue();
            cards.put(name, new MapCard(name, map));
        }

        // setup action listeners in each card
        // needs a reference to this class to assign correctly
        for (Card c : cards.values()) c.setComponentActions(this);

        // add all
        cards.forEach((str, card) -> this.add(card, str));
        master.add(this);

        // finally, make the menu screen visible
        switchScreen("menu");

        //TODO: AudioTesting
        //audioHandler.playSound(MusicTrack.TEST_MUSIC);
    }

    /**
     * Set up some Swing UI properties; override default values of UIManager.
     */
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
        if (currentCard != null) lastCard = currentCard;
        currentCard = cards.get(key);
        // force a redraw on the new card
        redraw();
    }

    /**
     * Start the game timer.
     */
    public void start() {
        game.unPauseGame();
        switchScreen(game.getWorld().getStartingMap().getMapName());
        (timer = new Timer(16, (e) -> redraw())).start();
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

    /**
     * Destroy the display and its components.
     */
    public void dispose() {
        master.dispose();
    }

    /**
     * Return the display's {@link AudioHandler}. This is used by the cards to
     * trigger sound effects.
     */
    public AudioHandler getAudioHandler() {
        return audioHandler;
    }

    @Override
    public void update(Observable o, Object arg) {
        // switch screen if need be (use arg)
        if (arg != null && arg instanceof String) {
            String str = (String) arg;
            if (str.equals("last"))
                switchScreen(lastCard.getName());
            else switchScreen(str);
        }
        else redraw();
    }
}

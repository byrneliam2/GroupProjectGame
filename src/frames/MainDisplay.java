package frames;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.audio.IAudioHandler;
import common.audio.MusicTrack;
import common.audio.SoundTrack;
import common.controller.IController;
import common.game.IGame;
import common.utils.DisplayValues;
import frames.cards.Card;
import frames.cards.MapCard;
import frames.cards.MenuCard;
import frames.cards.PauseCard;
import frames.cards.SettingsCard;
import gfx.ImageLoader;
import player.Bullet;
import save_load.SaveLoad;

/**
 * The MainDisplay represents the primary UI component for the front end
 * of the game. The class holds the master frame (which it adds itself to)
 * and a collection of Cards. Each Card represents a screen of the game
 * (see {@link Card} class for more information.) Uses the Strategy pattern
 * by using Cards which all have specific implementations.
 */
public class MainDisplay extends JComponent implements Observer {

    /* Swing attributes */
    private JFrame master;
    private Timer timer;

    /* Other UI attributes */
    private Map<String, Card> cards;
    private Card currentCard, lastCard;
    private IAudioHandler audioHandler;
    private IController controller;

    /* Game attributes */
    private IGame game;

    public MainDisplay(IGame g, IAudioHandler aud, IController cntrl) {
        game = g;
        audioHandler = aud;
        controller = cntrl;

        game.giveObserver(this);

        master = new JFrame("The Illusion of the Prophecy");
        currentCard = null;
        cards = new LinkedHashMap<>();

        // master frame setup
        master.setIconImage(ImageLoader.image("ui", "logo", true));
        master.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        master.setPreferredSize(new Dimension(DisplayValues.WIDTH, DisplayValues.HEIGHT));
        master.setResizable(false);
        master.setFocusable(true);
        master.setUndecorated(true);
        //master.setExtendedState(JFrame.MAXIMIZED_BOTH);

        master.addKeyListener(this.controller.getKeyAdapter());
        master.addMouseListener(this.controller.getMouseAdapter());
        master.addMouseMotionListener(this.controller.getMouseAdapter());

        // this component setup
        this.setLayout(new CardLayout());
        this.doSetup();

        master.pack();
        master.setVisible(true);
    }

    /* =========================================================================================== */

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

        // setup action listeners in each card
        // needs a reference to this class to assign correctly
        for (Card c : cards.values()) c.setComponentActions(this);

        // add all
        cards.forEach((str, card) -> this.add(card, str));
        master.add(this);

        // finally, make the menu screen visible
        menu();
    }

    /**
     * Set up some Swing UI properties (override default values of UIManager) to make
     * stock elements look more fitting (optional.)
     */
    @SuppressWarnings("unused")
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
     * Add all map cards to the collection and to this component.
     */
    private void doMapSetup() {
        // get model details and construct enough map cards to fit
        for (Map.Entry m : game.getWorld().getMaps().entrySet()) {
            String name = (String) m.getKey();
            map.Map map = (map.Map) m.getValue();
            cards.put(name, new MapCard(name, map, game));
            this.add(cards.get(name), name);
        }
    }

    /* =========================================================================================== */

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
     * Switch to the menu screen. At the same time, cue the menu music and reset all
     * controller commands so that none persist in new games.
     */
    private void menu() {
        switchScreen("menu");
        controller.reloadController();
        audioHandler.stop();
        audioHandler.playLoop(MusicTrack.MENU_MUSIC);
    }

    /* =========================================================================================== */

    /**
     * Load a new game. This consists of calling the newGame method on Game
     * and setting up the map cards.
     */
    public void newGame() {
        game.newGame();
        this.doMapSetup();
    }

    /**
     * Start a new game by opening the first world and starting the timer.
     */
    public void startGame() {
        switchScreen(game.getWorld().getStartingMap().getName());
        audioHandler.stop();
        audioHandler.playLoop(MusicTrack.GAME_MUSIC);
        startTimer();
    }

    /**
     * Pause the game.
     */
    private void pauseGame() {
        stopTimer();
        switchScreen("pause");
    }

    /**
     * Stop the game and return to the main menu.
     */
    private void stopGame() {
        stopTimer();
        menu();
    }

    /**
     * Save the current game.
     * @param selectedFile file selected
     */
    public void saveGame(File selectedFile) {
        game.saveGame(selectedFile.getAbsolutePath());
    }

    /**
     * Load a game from a save file.
     * @param selectedFile file selected
     */
    public void loadGame(File selectedFile) {
    	Bullet.bulletList.clear();
        IGame loadedGame = SaveLoad.loadGame(selectedFile);
        game.loadGame(loadedGame.getPlayer(), loadedGame.getWorld());
        this.doMapSetup();
        switchScreen(game.getPlayer().getMap().getName());
        audioHandler.stop();
        audioHandler.playLoop(MusicTrack.GAME_MUSIC);
        startTimer();
    }

    /* =========================================================================================== */

    /**
     * Switch to the first map and start the game redraw timer. This essentially
     * boots the game since it un-pauses it and allows the entity update
     * mechanism to execute.
     */
    public void startTimer() {
        (timer = new Timer(DisplayValues.FRAMERATE, (e) -> {
            redraw();
            controller.update();
        })).start();
        game.unPauseGame();
    }

    /**
     * Determine whether the timer is currently running.
     */
    public boolean isTimerRunning() {
        return timer.isRunning();
    }

    /**
     * Stop the game timer. This does not call to pause the game since this operation
     * is done manually before the display's timer is set to hold (see {@link game.Game#pauseGame}.
     */
    public void stopTimer() {
        timer.stop();
    }

    /* =========================================================================================== */

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

    public IAudioHandler getAudioHandler() {
        return audioHandler;
    }

    @Override
    public void update(Observable o, Object arg) {
        // switch screen if need be (use arg)
        if (arg != null && arg instanceof String) {
            String str = (String) arg;
            // list special cases
            switch (str) {
                //Switch Screens
                case "last":    switchScreen(lastCard.getName());         break;
                case "pause":   pauseGame();                              break;
                case "stop":    stopGame();                               break;
                case "unpause": startTimer();                             break;
                //Play Audio
                case "shoot":   audioHandler.playSound(SoundTrack.SHOOT); break;
                case "hurt":   audioHandler.playSound(SoundTrack.HURT);   break;
                default:        switchScreen(str);                        break;
            }
        }
        else redraw();
    }
}

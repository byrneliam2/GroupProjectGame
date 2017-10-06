package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import audio.common.*;
import frames.MainDisplay;
import gfx.GraphicsUtilities;
import gfx.ImageLoader;
import gfx.LabelButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

/**
 * The MenuCard represents the main menu of the game. This contains buttons that start,
 * load or end the game, along with a link to the {@link SettingsCard}.
 */
public class MenuCard extends Card {

    public MenuCard(String n) {
        super(n);
        setBackground(ImageLoader.image("ui","menu", false));
    }

    @Override
    protected void doUISetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // put the logo in separately
        components.put("logo", GraphicsUtilities.produceSticker(
                ImageLoader.image("ui", "logo", true), 0.5f));
        // use a loop to load all the buttons into the list
        String[] files = {"new", "load", "settings", "info", "exit"};
        //String[] files = {"load", "settings", "info", "exit"};
        //components.put("new", new LabelButton("New Game", 225, 75));
        for (String s : files) {
            components.put(s, GraphicsUtilities.produceButton(
                    ImageLoader.image("ui", "bu_" + s, true),
                    ImageLoader.image("ui", "bu_" + s + "_r", false), 0.5f));
        }
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
            if (!(m.getValue() instanceof JButton)) continue;
            final String str = (String) m.getKey();
            final JButton btn = (JButton) m.getValue();
            btn.addActionListener(e -> {
                dsp.getAudioHandler().playSound(SoundTrack.CLICK);
                switch (str) {
                    case "new":
                        dsp.newGame();
                        dsp.startGame();
                        break;
                    case "load":
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(".."));
                        fileChooser.setDialogTitle("Select a <ext> file to load");
                        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                            dsp.loadGame(fileChooser.getSelectedFile());
                        }
                        break;
                    case "settings":
                        dsp.update(null, "settings");
                        break;
                    case "info":
                        showInfo();
                        break;
                    case "exit":
                        if (JOptionPane.showConfirmDialog(this,
                                "Are you sure you wish to exit?", "Exit Game",
                            JOptionPane.YES_NO_OPTION) == 0)
                            System.exit(0);
                        break;
                }
            });
        }
    }

    @Override
    public void redraw() {
        panel.removeAll();
        // add the components in a top to bottom order, adding glue where we want space
        panel.add(Box.createVerticalGlue());
        panel.add(components.get("logo"));
        panel.add(Box.createRigidArea(new Dimension(100, 25)));
        panel.add(components.get("new"));
        panel.add(components.get("load"));
        panel.add(components.get("settings"));
        panel.add(components.get("info"));
        panel.add(components.get("exit"));
        panel.add(Box.createVerticalGlue());
    }

    /**
     * Show some information about the game and those who made it.
     */
    private void showInfo() {
        String about = "THE ILLUSION OF THE PROPHECY\n" +
                "A game made for SWEN 222 at Victoria University of Wellington.\n\n" +
                "Made by:\n- Liam Byrne (github.com/byrneliam2)\n- James Watt (github.com/Hiccup246)\n" +
                "- Thomas Edwards (github.com/tomBeep)\n- Mohsen Javaher (github.com/javahemohs)\n" +
                "- Andrew McManaway (github.com/McManaway1)\n\n" +
                "17 October 2017";
        JOptionPane.showMessageDialog(this, about, "Credits", JOptionPane.PLAIN_MESSAGE);
    }
}

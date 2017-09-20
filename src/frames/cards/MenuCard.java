package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import gfx.GraphicsUtilities;
import gfx.ImageLoader;

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
    protected void doSetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // main game logo
        components.put("logo", GraphicsUtilities.produceSticker(
                ImageLoader.image("ui", "logo", true), 0.5f));
        // play button
        components.put("new", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_new", true),
                ImageLoader.image("ui", "bu_new_r", false), 0.5f));
        // load button
        components.put("load", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_load", true),
                ImageLoader.image("ui", "bu_load_r", false), 0.5f));
        // settings button
        components.put("settings", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_settings", true),
                ImageLoader.image("ui", "bu_settings_r", false), 0.5f));
        // info button?
        components.put("info", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_info", true),
                ImageLoader.image("ui", "bu_info_r", false), 0.5f));
        // exit button
        components.put("exit", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_exit", true),
                ImageLoader.image("ui", "bu_exit_r", false), 0.5f));
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
            if (!(m.getValue() instanceof JButton)) continue;
            final String str = (String) m.getKey();
            final JButton btn = (JButton) m.getValue();
            btn.addActionListener(e -> {
                switch (str) {
                    case "new":
                        dsp.update(null, "pause");
                        break;
                    case "load":
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new File(".."));
                        fileChooser.setDialogTitle("Select a <ext> file to load");
                        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {}
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
                "Made by Liam Byrne, James Watt, Thomas Edwards, Mohsen Javaher and " +
                "Andrew McManaway.\n\n" +
                "17 October 2017";
        JOptionPane.showMessageDialog(this, about, "Credits", JOptionPane.PLAIN_MESSAGE);
    }
}

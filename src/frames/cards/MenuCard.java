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
import java.util.Map;

/**
 * The MenuCard represents the main menu of the game. This contains buttons that start,
 * load or end the game, along with a link to the {@link SettingsCard}.
 */
public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("ui","menu", false));
    }

    @Override
    protected void doSetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // main game logo
        components.put("logo", GraphicsUtilities.produceSticker(ImageLoader.image("ui", "logo", true), true));
        // play button
        components.put("newgame", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_newgame", false),
                null, true));
        // load button
        components.put("loadgame", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_loadgame", false),
                null, true));
        // settings button
        components.put("settings", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_settings", false),
                null, true));
        // info button?

        // exit button
        components.put("exit", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_exit", false),
                null, true));
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
            if (!(m.getValue() instanceof JButton)) continue;
            final String str = (String) m.getKey();
            final JButton btn = (JButton) m.getValue();
            btn.addActionListener(e -> {
                switch (str) {
                    case "newgame":
                        dsp.update(null, "settings");
                        break;
                    case "loadgame":
                        dsp.update(null, "settings");
                        break;
                    case "settings":
                        dsp.update(null, "settings");
                        break;
                    case "exit":
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
        panel.add(Box.createRigidArea(new Dimension(100, 50)));
        panel.add(components.get("newgame"));
        panel.add(components.get("loadgame"));
        panel.add(components.get("settings"));
        panel.add(components.get("exit"));
        panel.add(Box.createVerticalGlue());
    }
}

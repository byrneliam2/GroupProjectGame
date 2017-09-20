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
import java.util.Map;

/**
 * The MenuCard represents the main menu of the game. This contains buttons that start,
 * load or end the game, along with a link to the {@link SettingsCard}.
 */
public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
    }

    @Override
    protected void doSetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // main game logo
        //components.put("logo", GraphicsUtilities.produceSticker(ImageLoader.image("pause.jpg")));
        // play button
        components.put("newgame", GraphicsUtilities.produceButton(
                ImageLoader.image("bu_newgame.jpg"),
                null, true));
        // load button
        components.put("loadgame", GraphicsUtilities.produceButton(
                ImageLoader.image("bu_loadgame.jpg"),
                null, true));
        // settings button
        components.put("settings", GraphicsUtilities.produceButton(
                ImageLoader.image("bu_settings.jpg"),
                null, true));
        // info button?

        // exit button
        components.put("exit", GraphicsUtilities.produceButton(
                ImageLoader.image("bu_exit.jpg"),
                null, true));
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
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
        panel.add(Box.createVerticalGlue());
        //panel.add(components.get("logo"));
        panel.add(components.get("newgame"));
        //panel.add(Box.createRigidArea(new Dimension(100, 50)));
        panel.add(components.get("loadgame"));
        panel.add(components.get("settings"));
        panel.add(components.get("exit"));
        panel.add(Box.createVerticalGlue());
    }
}

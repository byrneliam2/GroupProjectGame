package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.GraphicsUtilities;
import gfx.ImageLoader;

import javax.swing.*;

/**
 * The MenuCard represents the main menu of the game. This contains buttons that start,
 * load or end the game, along with a link to the {@link SettingsCard}.
 */
public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
        doSetup();
    }

    @Override
    protected void doSetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // main game logo
        //panel.add(GraphicsUtilities.produceSticker(ImageLoader.image("pause.jpg")));
        // play button
        components.put("play", GraphicsUtilities.produceButton(ImageLoader.image("bu_exit.jpg"),
                ImageLoader.image("pause.jpg"), true));
        // load button
        components.put("load", GraphicsUtilities.produceButton(ImageLoader.image("bu_exit.jpg"),
                ImageLoader.image("pause.jpg"), true));
        // settings button
        components.put("settings", GraphicsUtilities.produceButton(ImageLoader.image("bu_exit.jpg"),
                ImageLoader.image("pause.jpg"), true));
        // info button?

        // exit button
        components.put("exit", GraphicsUtilities.produceButton(ImageLoader.image("bu_exit.jpg"),
                ImageLoader.image("pause.jpg"), true));
    }

    @Override
    public void redraw() {
        panel.removeAll();
        // add the components in a top to bottom order, adding glue where we want space
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(components.get("play"));
        //panel.add(Box.createRigidArea(new Dimension(100, 50)));
        panel.add(components.get("load"));
        panel.add(components.get("settings"));
        panel.add(components.get("exit"));
        panel.add(Box.createVerticalGlue());
    }
}

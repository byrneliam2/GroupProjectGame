package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.GraphicsUtilities;
import gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;

/**
 * The MenuCard represents the main menu of the game. This contains buttons that start,
 * load or end the game, along with a link to the {@link SettingsCard}.
 */
public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
    }

    @Override
    public void redraw() {
        panel.removeAll();
        // add the components in a top to bottom order, adding glue where we want space
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        //panel.add(GraphicsUtilities.produceSticker(ImageLoader.image("pause.jpg")));
        panel.add(GraphicsUtilities.produceButton(ImageLoader.image("bu_exit.jpg"),
                ImageLoader.image("pause.jpg"), true));
        //panel.add(Box.createRigidArea(new Dimension(100, 50)));
        panel.add(GraphicsUtilities.produceButton(ImageLoader.image("bu_exit.jpg"),
                ImageLoader.image("pause.jpg"), true));
        panel.add(Box.createVerticalGlue());
    }
}

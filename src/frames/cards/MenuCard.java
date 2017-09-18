package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;

import javax.swing.*;

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
        // TODO extract this to GraphicsUtilities
        JButton exit = new JButton();
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setIcon(new ImageIcon(ImageLoader.image("bu_exit.jpg")));
        exit.setRolloverEnabled(true);
        exit.setRolloverIcon(new ImageIcon(ImageLoader.image("pause.jpg")));
        exit.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(exit);
    }
}

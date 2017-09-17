package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
        panel.add(new JButton(new ImageIcon(ImageLoader.image("bu_exit.jpg"))));
    }

    @Override
    public void redraw() {
        //
    }
}

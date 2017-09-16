package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;

import javax.swing.*;

public class PauseCard extends Card {

    public PauseCard() {
        setBackground(ImageLoader.image("pause.jpg"));
        this.add(new JLabel(new ImageIcon(background)));
    }

    @Override
    public void redraw() {
        //
    }
}

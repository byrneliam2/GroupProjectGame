package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;

public class PauseCard extends Card {

    public PauseCard() {
        setBackground(ImageLoader.image("pause.jpg"));
    }

    @Override
    public void redraw() {
        //
    }
}

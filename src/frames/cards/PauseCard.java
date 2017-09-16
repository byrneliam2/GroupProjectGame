package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.GraphicsUtilities;
import gfx.ImageLoader;

public class PauseCard extends Card {

    public PauseCard() {
        setBackground(ImageLoader.image("pause.jpg"));
        GraphicsUtilities.giveImage(this, background);
    }

    @Override
    public void redraw() {
        //
    }
}

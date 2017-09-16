package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.GraphicsUtilities;
import gfx.ImageLoader;

public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
        GraphicsUtilities.giveImage(this, background);
    }

    @Override
    public void redraw() {
        //
    }
}

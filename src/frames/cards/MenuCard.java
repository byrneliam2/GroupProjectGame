package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import gfx.GraphicsUtilities;
import gfx.ImageLoader;

import java.awt.*;

public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        GraphicsUtilities.drawBackground(this, background, (Graphics2D) g);
    }

    @Override
    public void redraw() {
        //
    }
}

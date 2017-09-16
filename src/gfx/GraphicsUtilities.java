package gfx;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import frames.cards.Card;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The GraphicsUtilities class takes the complexities of dealing with images
 * away from the Card classes and brings it into a more package-localised
 * environment. This way, any operations or extra images that need to be loaded
 * are dealt with internally within the gfx component.
 */
public class GraphicsUtilities {

    /**
     * Draw the given background image onto the specified card.
     * @param c card to draw on
     * @param img image to add
     * @param g graphics object
     */
    public static void drawBackground(Card c, BufferedImage img, Graphics2D g) {
        g.drawImage(img, 0, 0, c.getWidth(), c.getHeight(), null);
    }
}

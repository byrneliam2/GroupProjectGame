package gfx;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.cards.Card;

import javax.swing.*;
import java.awt.*;

/**
 * The GraphicsUtilities class takes the complexities of dealing with images
 * away from the Card classes and brings it into a more package-localised
 * environment. This way, any operations or extra images that need to be loaded
 * are dealt with internally within the gfx component.
 */
public class GraphicsUtilities {

    /**
     * Add an image to the specified card.
     * @param c card
     * @param img image
     */
    public static void giveImage(Card c, Image img) {
        c.add(new JLabel(new ImageIcon(img)));
    }
}

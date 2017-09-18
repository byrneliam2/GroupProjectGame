package gfx;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * The GraphicsUtilities class takes the complexities of dealing with images
 * away from the Card classes and brings it into a more package-localised
 * environment. This way, any operations or extra images that need to be loaded
 * are dealt with internally within the gfx component.
 */
public class GraphicsUtilities {

    /**
     * Creates and returns a transparent, roll-over enabled button with a set image.
     * This button is automatically centered for purposes such as menus and clickable
     * dialogs.
     * @return new JButton
     */
    public static JButton produceButton(BufferedImage main, BufferedImage roll) {
        JButton button = new JButton();
        // set visual attributes
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(main));
        // set rollover visuals
        button.setRolloverEnabled(true);
        button.setRolloverIcon(new ImageIcon(roll));
        // force middle alignment
        button.setAlignmentX(0.5f);
        // done
        return button;
    }

}

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
     * @return new JButton
     */
    public static JButton produceButton(BufferedImage main, BufferedImage roll) {
        JButton button = new JButton();
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(main));
        button.setRolloverEnabled(true);
        button.setRolloverIcon(new ImageIcon(roll));
        button.setVerticalAlignment(SwingConstants.CENTER);
        return button;
    }

}

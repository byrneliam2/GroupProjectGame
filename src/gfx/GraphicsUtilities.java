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
     * @param main image to attach
     * @param roll image to appear on button roll-over
     * @param centerx sets whether to center the x alignment of this component
     * @return new JButton
     */
    public static JButton produceButton(BufferedImage main, BufferedImage roll, boolean centerx) {
        JButton button = new JButton();
        // set visual attributes
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(main));
        // set rollover visuals
        button.setRolloverEnabled(true);
        button.setRolloverIcon(new ImageIcon(roll));
        // force middle alignment
        if (centerx) button.setAlignmentX(0.5f);
        // done
        return button;
    }

    /**
     * Creates and returns a sticker, or a non-interactive label, with the specified image.
     * @param main image to attach
     * @param centerx sets whether to center the x alignment of this component
     * @return new label
     */
    public static JLabel produceSticker(BufferedImage main, boolean centerx) {
        JLabel sticker = new JLabel();
        sticker.setIcon(new ImageIcon(main));
        if (centerx) sticker.setAlignmentX(0.5f);
        return sticker;
    }

}

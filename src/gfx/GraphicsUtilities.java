package gfx;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The GraphicsUtilities class takes the complexities of dealing with images
 * away from the Card classes and brings it into a more package-localised
 * environment. This way, any operations or extra images that need to be loaded
 * are dealt with internally within the gfx component.
 */
public class GraphicsUtilities {

    public static final int BUTTON_SIZE = 225;

    /**
     * Creates and returns a transparent, roll-over enabled button with a set image.
     * Button images have a dimension of 225 x 75 pixels.
     * @param main image to attach
     * @param roll image to appear on button roll-over, or null if none specified
     * @param alignment sets x alignment value (0 for left, 1 for right)
     * @return new JButton
     */
    public static JButton produceButton(BufferedImage main, BufferedImage roll, float alignment) {
        if (main == null) throw new IllegalArgumentException("Primary button image cannot be null.");
        JButton button = new JButton();
        // set visual attributes
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(main));
        // set rollover visuals
        if (roll != null) {
            button.setRolloverEnabled(true);
            button.setRolloverIcon(new ImageIcon(roll));
        }
        // set alignment
        button.setAlignmentX(alignment);
        // done
        return button;
    }

    /**
     * Creates and returns a sticker, or a non-interactive label, with the specified image.
     * @param main image to attach
     * @param alignment sets x alignment value (0 for left, 1 for right)
     * @return new JLabel
     */
    public static JLabel produceSticker(BufferedImage main, float alignment) {
        if (main == null) throw new IllegalArgumentException("Sticker image cannot be null.");
        JLabel sticker = new JLabel();
        sticker.setIcon(new ImageIcon(main));
        sticker.setAlignmentX(alignment);
        return sticker;
    }

    /**
     * Creates and returns a slider that is locked to be the same width as the buttons.
     * The slider has a default range of 0 to 100 and an initial value of 50.
     * @return new JSlider
     */
    public static JSlider produceSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setMaximumSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        slider.setBackground(Color.WHITE);
        slider.setForeground(Color.BLUE);
        return slider;
    }
}

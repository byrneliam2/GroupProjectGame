package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Card represents a screen of the game. Each Card has attributes and functions
 * that relate specifically to its purpose, such as MenuCards hosting menu-related
 * methods and MapCards with heavy model-based functionality.
 */
public abstract class Card extends JPanel {

    protected BufferedImage background;
    private JLabel bgimage;

    /**
     * Set the background image of this card. The image is placed using
     * a JLabel that has the image as its icon.
     * @param bg image to set background to
     *           TODO make background resize to frame
     */
    protected void setBackground(BufferedImage bg) {
        this.background = bg;
        this.bgimage = new JLabel(new ImageIcon(background));
        this.bgimage.setPreferredSize(new Dimension(MainDisplay.F_WIDTH, MainDisplay.F_HEIGHT));
        this.add(bgimage);
    }

    /**
     * Redraw the current card. Note that this method only draws the current card again,
     * it does not revalidate or repaint the component. The {@link #update()} method
     * should be called after redraw to ensure an observable update is performed.
     */
    public abstract void redraw();

    /**
     * Perform the operations required for Swing to update the component.
     */
    protected void update() {
        this.revalidate();
        this.repaint();
    }

    /**
     * A Card Entity is any animated element inside a Card. This does not include
     * separate Swing entities such as buttons and background images.
     */
    public class Entity {

        private BufferedImage image;

        public Entity(BufferedImage image) {
            this.image = image;
        }

        public BufferedImage getImage() {
            return image;
        }
    }
}

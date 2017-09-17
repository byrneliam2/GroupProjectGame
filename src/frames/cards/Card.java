package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * A Card represents a screen of the game. Each Card has attributes and functions
 * that relate specifically to its purpose, such as MenuCards hosting menu-related
 * methods and MapCards with heavy model-based functionality.
 */
public abstract class Card extends JPanel {

    /* Swing attributes */
    protected JLabel panel;

    /* Other attributes */
    protected BufferedImage background;

    /**
     * Set the background image of this card. This is done by adding the image
     * to the label panel that takes up the whole card.
     * @param bg image to set background to
     */
    protected void setBackground(BufferedImage bg) {
        this.background = bg;
        this.panel = new JLabel(new ImageIcon(background));
        this.add(panel);
    }

    /**
     * Perform the operations required for Swing to update the component.
     */
    public void update() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Redraw the current card. Note that this method only draws the current card again,
     * it does not revalidate or repaint the component. The {@link #update()} method
     * should be called after redraw to ensure an observable update is performed.
     */
    public abstract void redraw();

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

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
 * methods and MapCards with heavy model-driven functionality.
 */
public abstract class Card extends JPanel {



    /**
     * Redraw the current card.
     */
    public abstract void redraw();

    /**
     * A Card Entity is any animated element inside a Card.
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

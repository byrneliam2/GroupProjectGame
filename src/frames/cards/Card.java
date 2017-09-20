package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * A Card represents a screen of the game. Each Card has attributes and functions
 * that relate specifically to its purpose, such as MenuCards hosting menu-related
 * methods and MapCards with heavy model-based functionality.
 */
public abstract class Card extends JPanel {

    /* Swing attributes */
    JLabel panel;
    Map<String, JComponent> components;

    Card() {
        panel = new JLabel();
        components = new HashMap<>();

        // set the look of option panes
        // https://stackoverflow.com/questions/1951558/list-of-java-swing-ui-properties
        /*UIManager.put("OptionPane.background",new ColorUIResource(0,0,0));
        UIManager.put("Panel.background",new ColorUIResource(0,0,0));*/

        add(panel);
        doSetup();
    }

    /**
     * Perform the operations required for Swing to update the component.
     */
    public void update() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Set the background image of this card. This is done by adding the image
     * to the label panel that takes up the whole card. Many cards will have some
     * kind of background image that persists throughout its existence, therefore
     * this should be considered as a "setup" method that should be called
     * on creation.
     * @param bg image to set background to
     */
    @SuppressWarnings("WeakerAccess")
    public void setBackground(BufferedImage bg) {
        panel.setIcon(new ImageIcon(bg));
    }

    /**
     * Perform first time setup for this Card. This will usually involve
     * setting up whatever components this screen holds. MapCards will likely
     * use this method to set up their Entity list instead. If the
     * added components are interactive Swing components, then they will need to
     * be properly assigned by using the {@link #setComponentActions(MainDisplay)}
     * method from the display that holds the card.
     */
    protected abstract void doSetup();

    /**
     * Add actions to the components on screen, if there are any. This method
     * will usually only function if there are interactive components (buttons,
     * sliders, etc.) held in the card's reference map.
     * @param dsp display that holds this card. Used to link actions to events that
     *            occur in the main view class.
     */
    public abstract void setComponentActions(MainDisplay dsp);

    /**
     * Redraw the elements present on the current card. Note that this method only
     * draws the elements again, it does not revalidate or repaint the component.
     * The {@link #update()} method should be called after redraw to ensure an
     * observable update is performed.
     */
    public abstract void redraw();

    /**
     * A Card Entity is any animated element inside a Card. This does not include
     * separate Swing entities such as buttons and background images.
     */
    class Entity {

        private BufferedImage image;

        public Entity(BufferedImage image) {
            this.image = image;
        }

        public BufferedImage getImage() {
            return image;
        }
    }
}

package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import frames.cards.Card.EntityType;
import gfx.ImageUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
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

	/* Other attributes */
	private String name;

	/**
	 * Each Card has a name associated with it for locating purposes.
	 * @param n name
	 */
	Card(String n) {
		name = n;
		panel = new JLabel();
		components = new LinkedHashMap<>();

		add(panel);

		doUISetup();
	}

	/**
	 * Get the name of this Card.
	 */
	public String getName() {
		return name;
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
	 * on creation. This method must be manually called for maps since the image is
	 * not static across all maps.
	 * 
	 * @param bg image to set background to
	 */
	@SuppressWarnings("WeakerAccess")
	public void setBackground(BufferedImage bg) {
		panel.setIcon(new ImageIcon(bg));
	}

	/**
	 * Perform first time setup for this Card. This will usually involve
	 * setting up whatever components this screen holds. If the
	 * added components are interactive Swing components, then they will need to
	 * be properly assigned by using the {@link #setComponentActions(MainDisplay)}
	 * method from the display that holds the card.
	 */
	protected abstract void doUISetup();

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
	 * Declares the type of object the {@link Entity} represents.
	 */
	enum EntityType {
		ITEM, PLAYER, NPC, HEART, INVENTORY, STRING
	}
}

/**
 * A Card Entity represents an animated element inside a Card. This does not include
 * separate Swing entities such as buttons and background images. It does include
 * on-screen indicators that refer to the game directly, however. These are identified
 * using the SPECIAL tag for the Entity's {@link EntityType}. Small items that persist
 * for short periods of time, such as bullets, do not count as entities.
 */
class Entity {

	private Object object;
	private EntityType type;
	private BufferedImage image;
	private Point location;

	private static final int SIZE = 60;

	/**
	 * Using this constructor will scale the image associated with the entity.
	 * @param object   object that this entity represents
	 * @param type     type of the object
	 * @param image    image of the object
	 * @param location location of the object
	 * @param size     size to scale to; if 0, a default size of {@link #SIZE} will be used
	 */
	Entity(Object object, EntityType type, BufferedImage image, Point location, int size) {
		this.object = object;
		this.type = type;
		this.image = ImageUtilities.scale(image, size != 0 ? size : SIZE, size != 0 ? size : SIZE);
		this.location = location;
	}
	
	/**
	 * This constructor doesn't scale the associated image.
	 */
	Entity(Object object, EntityType type, BufferedImage image, Point location) {
		this.object = object;
		this.type = type;
		this.image = image;
		this.location = location;
	}

	public Object getObject() {
		return object;
	}

	public EntityType getType() {
		return type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void setImage(BufferedImage newImage) {
		this.image = newImage;
	}
}

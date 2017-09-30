package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import game.IGame;
import gfx.ImageLoader;
import gfx.ImageUtilities;
import items.Item;
import player.Bullet;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * The MapCard displays the state of a Map, including all Entities on screen.
 * TODO Dialogue popups
 * TODO Inventory screen
 * TODO Proper player health [selected]
 * TODO Player animations
 */
public class MapCard extends Card {

	private List<Entity> entities;
	private List<Entity> elements;
	private map.Map map;
	private IGame game;

	private static final int HEART_X = 50;

	public MapCard(String n, map.Map map, IGame game) {
		super(n);

		this.entities = new ArrayList<>();
		this.elements = new ArrayList<>();

		this.map = map;
		this.game = game;

		setBackground(ImageUtilities.scale(ImageLoader.image("MapImages", map.getBackgroundLayer(), true),
				MainDisplay.WIDTH, MainDisplay.HEIGHT));

		addEntities();
		addUIEntities();
	}

	/**
	 * Add all entities to the map. This is done outside of the setup method since the map is not constructed
	 * beforehand and also because this setup does not relate to Swing components.
	 */
	private void addEntities() {
		// add player
		addGameEntity(new Entity(game.getPlayer(), EntityType.PLAYER, ImageLoader.image("game", "playerRect", true),
				new Point(game.getPlayer().getxLocation(), game.getPlayer().getyLocation()), 0));
		// add all NPCs
		map.getNPCS().forEach(npc -> addGameEntity(new Entity(npc, EntityType.NPC,
				ImageLoader.image("game", "bug", true), new Point(npc.getxLocation(), npc.getyLocation()), 0)));
		// add all items
		map.getItems()
				.forEach(item -> addGameEntity(new Entity(item, EntityType.ITEM,
						ImageLoader.image("ItemPictures", item.getImageFileName(), true),
						new Point(item.getX(), item.getY()), 0)));
	}

	/**
	 * Add UI entities to the screen.
	 */
	private void addUIEntities() {
		// add player health
		for (int i = 0; i < game.getPlayer().getHealth(); i++) {
			addUIEntity(new Entity(game.getPlayer(), EntityType.HEART, ImageLoader.image("game", "heart", true),
					new Point(HEART_X + (i * HEART_X), 0), 50));
		}
	}

	/**
	 * Update the location of all game entities, if they are indeed a game entity.
	 * UI entities are dealt with external of this method.
	 */
	private void updateEntities() {
		List<Entity> itemsToRemove = new ArrayList<>();
		for (Entity e : entities) {
			Object o = e.getObject();
			switch (e.getType()) {
			case ITEM:
				Item i = (Item) o;
				if (i.getPack() != null)
					itemsToRemove.add(e);
				else
					e.setLocation(new Point(i.getX(), i.getY()));
				break;
			case PLAYER:
			case NPC:
				Player p = (Player) o; // since an NPC is a Player
				e.setLocation(new Point(p.getxLocation(), p.getyLocation()));
				break;
			case BULLET:
				Bullet b = (Bullet) o;
				// e.setLocation(new Point(b.getX(), b.getY()));
				break;
			// everything else goes here
			default:
				updateElements();
				break;
			}
		}
		entities.removeAll(itemsToRemove);
	}

	/**
	 * Update the elements list.
	 */
	private void updateElements() {
		List<Entity> elementsToRemove = new ArrayList<>();
		for (Entity e : elements) {
			switch (e.getType()) {
			case HEART:
				// if (game.getPlayer().getHealth() != hearts.size()) addHearts();
				break;
			case DIALOGUE:
				break;
			}
		}
		elements.removeAll(elementsToRemove);
	}

	private void addGameEntity(Entity e) {
		entities.add(e);
	}

	private void addUIEntity(Entity e) {
		elements.add(e);
	}

	@Override
	protected void doUISetup() {
		panel.setLayout(null);
	}

	@Override
	public void setComponentActions(MainDisplay dsp) {
	}

	@Override
	public void redraw() {
		// reset the component lists
		panel.removeAll();
		updateEntities();
		// draw the lot
		Consumer<Entity> draw = (e) -> {
			JLabel l = new JLabel(new ImageIcon(e.getImage()));
			panel.add(l);
			l.setBounds(e.getLocation().x, e.getLocation().y, e.getImage().getWidth(), e.getImage().getHeight());
		};
		for (Entity e : entities)
			draw.accept(e);
		for (Entity e : elements)
			draw.accept(e);

		for (int i = 0; i < Bullet.bulletList.size(); i++) {
			Bullet b = Bullet.bulletList.get(i);
			Image img = Bullet.bulletImg;
			JLabel l = new JLabel(new ImageIcon(Bullet.bulletImg));
			panel.add(l);
			l.setBounds((int) b.getX(), (int) b.getY(), img.getWidth(null), img.getHeight(null));
		}
	}
}

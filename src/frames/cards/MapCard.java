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

/**
 * The MapCard displays the state of a Map, including all Entities on screen.
 * TODO Dialogue popups
 * TODO Inventory screen
 * TODO Player animations
 * TODO Add mouse crosshair
 * TODO adding of entities mid-game
 */
public class MapCard extends Card {

	/* Primary attributes */
	private List<Entity> entities;
	private List<Entity> elements;
	private map.Map map;
	private IGame game;

	/* Secondary attributes */
	private int heartIndex;
	private int frameIndex;

	/* Constants */
	private static final int ELEMENT_LOC_A = 50;

	public MapCard(String n, map.Map map, IGame game) {
		super(n);

		this.entities = new ArrayList<>();
		this.elements = new ArrayList<>();
		this.map = map;
		this.game = game;

		setBackground(ImageUtilities.scale(
				ImageLoader.image("MapImages", map.getBackgroundLayer(), true),
				MainDisplay.WIDTH, MainDisplay.HEIGHT));

		addInitialEntities();
		addUIEntities();
	}

	/* ======================================= ADDING ============================================ */
	/* ======================= Are only called upon creation of card ============================= */

	/**
	 * Add all entities to the map. This is done outside of the setup method since the map is not constructed
	 * beforehand and also because this setup does not relate to Swing components.
	 */
	private void addInitialEntities() {
		// add player
		addGameEntity(new Entity(game.getPlayer(), EntityType.PLAYER,
				ImageLoader.image("playerImages", "playerRect", true),
						new Point(game.getPlayer().getxLocation(), game.getPlayer().getyLocation()), 0)
		);
		// add all NPCs
		map.getNPCS().forEach(npc -> addGameEntity(new Entity(npc, EntityType.NPC,
				ImageLoader.image("npcImages", "bug", true),
				new Point(npc.getxLocation(), npc.getyLocation()), 0))
		);
		// add all items
		map.getItems().forEach(item -> addGameEntity(new Entity(item, EntityType.ITEM,
				ImageLoader.image("ItemPictures", item.getImageFileName(), true),
				new Point(item.getX(), item.getY()), 0))
		);
	}

	/**
	 * Add UI entities to the screen. To avoid confusion between the lists, UI entities are
	 * referred to as "elements."
	 */
	private void addUIEntities() {
		// add player health
		for (int i = 0; i < game.getPlayer().getHealth(); i++) {
			addUIEntity(new Entity(game.getPlayer(), EntityType.HEART,
					ImageLoader.image("game", "heart", true),
					new Point(heartIndex = ELEMENT_LOC_A + (i * ELEMENT_LOC_A), 0),
					ELEMENT_LOC_A));
		}
		// add inventory items
		for (int i = 0; i < game.getPlayer().getBackpack().getInventorySize(); i++) {
			addUIEntity(new Entity(game.getPlayer(), EntityType.ITEMFRAME,
					ImageLoader.image("game", "itemframe", true),
					new Point(frameIndex = MainDisplay.WIDTH - ELEMENT_LOC_A - (i * ELEMENT_LOC_A), 5),
					ELEMENT_LOC_A));
		}
		// add dialogue, if any
		//
	}

	/* ====================================== UPDATERS =========================================== */
	/* ============================== Called on each redraw ====================================== */

	/**
	 * Update the location of all game entities, if they are indeed a game entity.
	 * UI entities are dealt with external of this method.
	 */
	private void updateEntities() {
		List<Entity> itemsToRemove = new ArrayList<>();
		int numItems = 0;
		for (Entity e : entities) {
			Object o = e.getObject();
			switch (e.getType()) {
			case ITEM:
				Item i = (Item) o;
				if (i.getPack() != null) itemsToRemove.add(e);
				else {
					e.setLocation(new Point(i.getX(), i.getY()));
					numItems++;
				}
				break;
			case PLAYER: case NPC:
				Player p = (Player) o; // since an NPC is a Player
				if (p.isDead()) itemsToRemove.add(e);
				else e.setLocation(new Point(p.getxLocation(), p.getyLocation()));
				break;
			}
		}
		entities.removeAll(itemsToRemove);
		if (numItems < map.getItems().size()) {}
	}

	/**
	 * Update the UI elements list.
	 */
	private void updateElements() {
		List<Entity> elementsToRemove = new ArrayList<>();
		int numHearts = 0;
		for (Entity e : elements) {
			switch (e.getType()) {
			case HEART:
				if (++numHearts > game.getPlayer().getHealth())
					elementsToRemove.add(e);
				break;
			case ITEMFRAME:
				break;
			case DIALOGUE:
				break;
			}
		}
		elements.removeAll(elementsToRemove);
	}

	/**
	 * Draw the bullets that are currently present in the game world. These do not
	 * count as entities since they are have a lesser time of existence and so it
	 * is not worth counting them as entities.
	 */
	private void updateBullets() {
		for (int i = 0; i < Bullet.bulletList.size(); i++) {
			Bullet b = Bullet.bulletList.get(i);
			Image img = b.getOwner() == game.getPlayer() ?
					Bullet.playerBullet1 : Bullet.npcBullet1;
			draw(img, (int) b.getX() - Bullet.bulletSize/2,
					(int) b.getY() - Bullet.bulletSize/2,
					img.getWidth(null), img.getHeight(null));
		}
	}

	/* ===================================== UTILITIES ========================================= */

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
	public void setComponentActions(MainDisplay dsp) {}

	@Override
	public void redraw() {
		// reset the component lists
		panel.removeAll();
		// do updates
		updateEntities();
		updateElements();
		updateBullets();
		// draw the lot
		for (Entity e : entities)
			draw(e.getImage(), e.getLocation().x, e.getLocation().y,
					e.getImage().getWidth(), e.getImage().getHeight());
		for (Entity e : elements)
			draw(e.getImage(), e.getLocation().x, e.getLocation().y,
					e.getImage().getWidth(), e.getImage().getHeight());
	}

	/**
	 * Draw the entity by adding it to a JLabel and specifying its position.
	 */
	private void draw(Image image, int x, int y, int w, int h) {
		JLabel l = new JLabel(new ImageIcon(image));
		panel.add(l);
		l.setBounds(x, y, w, h);
	}
}

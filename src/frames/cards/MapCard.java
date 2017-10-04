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
import java.util.stream.Collectors;

/**
 * The MapCard displays the state of a Map, including all Entities on screen. The MapCard's state
 * at any time is representative of the current game, using a combination of specially-typed
 * {@link Card.Entity}s.
 * TODO Dialogue popups
 * TODO Player animations
 */
public class MapCard extends Card {

	/* Primary attributes */
	private List<Entity> statics;
	private List<Entity> dynamics;
	private map.Map map;
	private IGame game;

	/* Constants */
	private static final int ELEMENT_LOC_A = 50;

	public MapCard(String n, map.Map map, IGame game) {
		super(n);

		this.statics  = new ArrayList<>();
		this.dynamics = new ArrayList<>();
		this.map = map;
		this.game = game;

		setBackground(ImageUtilities.scale(
				ImageLoader.image("MapImages", map.getBackgroundLayer(), true),
				MainDisplay.WIDTH, MainDisplay.HEIGHT));

		addStaticEntities();
		addDynamicEntities(null, true);
	}

	/* ========================================== ADDING ========================================= */

	/**
	 * Add all static entities to the map. Static entities are ones that are created as part of the map
	 * and can be removed, but not added back. This is done outside of the setup method since the map
	 * is not constructed beforehand and also because this setup does not relate to Swing components.
	 */
	private void addStaticEntities() {
		// add player
		addStaticEntity(new Entity(game.getPlayer(), EntityType.PLAYER,
				ImageLoader.image("playerImages", "playerRect", true),
						new Point(game.getPlayer().getxLocation(), game.getPlayer().getyLocation()), 0)
		);
		// add all NPCs
		map.getNPCS().forEach(npc -> addStaticEntity(new Entity(npc, EntityType.NPC,
				ImageLoader.image("npcImages", "bug", true),
				new Point(npc.getxLocation(), npc.getyLocation()), 0))
		);
	}

	/**
	 * Add dynamic entities to the screen. Dynamic entities have the ability to be added in dynamically
	 * (hence the name) as well as everything a static entity can do.
	 */
	private void addDynamicEntities(EntityType type, boolean all) {
		if (type == EntityType.ITEM || all)
			// add all items
			map.getItems().forEach(item -> addDynamicEntity(new Entity(item, EntityType.ITEM,
					ImageLoader.image("ItemPictures", item.getImageFileName(), true),
					new Point(item.getX(), item.getY()), 0))
			);
		if (type == EntityType.HEART || all)
			// add player health
			for (int i = 0; i < game.getPlayer().getHealth(); i++) {
				addDynamicEntity(new Entity(game.getPlayer(), EntityType.HEART,
						ImageLoader.image("game", "heart", true),
						new Point(ELEMENT_LOC_A + (i * ELEMENT_LOC_A), 0),
						ELEMENT_LOC_A));
			}
		if (type == EntityType.INVENTORY || all)
			// add inventory items
			for (int i = 0; i < game.getPlayer().getBackpack().getInventorySize(); i++) {
				addDynamicEntity(new Entity(game.getPlayer(), EntityType.INVENTORY,
						ImageLoader.image("ItemPictures", game.getPlayer().getBackpack()
								.getInventory().get(i).getImageFileName(), true),
						new Point(MainDisplay.WIDTH - (2 * ELEMENT_LOC_A) - (i * ELEMENT_LOC_A), 5),
						ELEMENT_LOC_A));
			}
		// noinspection StatementWithEmptyBody
		if (type == EntityType.STRING || all) {
			// add dialogue, if any
			String str = "Hello!";
			addDynamicEntity(new Entity(str, EntityType.STRING,
					null, new Point(MainDisplay.WIDTH/2, MainDisplay.HEIGHT - ELEMENT_LOC_A),
					ELEMENT_LOC_A));
		}
	}

	/* ========================================= UPDATERS ======================================== */

	/**
	 * Update the location of all static entities and remove any that do not exist within the map
	 * as of the method call.
	 */
	private void updateStaticEntities() {
		List<Entity> toRemove = new ArrayList<>();
		for (Entity e : statics) {
			Object o = e.getObject();
			switch (e.getType()) {
				// only static entities (ones that can't be dynamically added) are Player instances
				case PLAYER: case NPC:
					Player p = (Player) o; // since an NPC is a Player
					if (p.isDead()) toRemove.add(e);
					else e.setLocation(new Point(p.getxLocation(), p.getyLocation()));
					break;
			}
		}
		statics.removeAll(toRemove);
	}

	/**
	 * Update the dynamic entities list.
	 */
	private void updateDynamicEntities() {
		List<Entity> toRemove = new ArrayList<>();
		int numHearts = 0, numItems = 0, numInventory = 0;
		for (Entity e : dynamics) {
			switch (e.getType()) {
				case ITEM:
					Item i = (Item) e.getObject();
					if (i.getPack() != null) toRemove.add(e);
					else {
						e.setLocation(new Point(i.getX(), i.getY()));
						numItems++;
					}
					break;
				case HEART:
					if (++numHearts > game.getPlayer().getHealth())
						toRemove.add(e);
					break;
				case INVENTORY:
					numInventory++;
					break;
				case STRING:
					break;
			}
		}
		dynamics.removeAll(toRemove);

		// update dynamic items accordingly
		EntityType type = null;
		Consumer<EntityType> dyn = t -> {
			removeAllDynamicsOfType(t);
			addDynamicEntities(t, false);
		};
		if (numItems < map.getItems().size()) 				type = EntityType.ITEM;
		if (numHearts < game.getPlayer().getHealth()) 		type = EntityType.HEART;
		if (numInventory < game.getPlayer().getBackpack()
				.getInventorySize()) 						type = EntityType.INVENTORY;
		if (type != null) dyn.accept(type);
	}

	/**
	 * Draw the bullets that are currently present in the game world. These do not
	 * count as entities since they are have a lesser time of existence and so it
	 * is not worth counting them as entities.
	 */
	private void updateBullets() {
		for (int i = 0; i < Bullet.bulletList.size(); i++) {
			Bullet b = Bullet.bulletList.get(i);
			Image img = b.getBulletPic();
			draw(img, (int) b.getX() - Bullet.bulletSize/2,
					(int) b.getY() - Bullet.bulletSize/2,
					img.getWidth(null), img.getHeight(null));
		}
	}

	/**
	 * Check if the game is over. If so, present a message to the player and stop the game
	 * upon acknowledgment.
	 */
	private void checkGameOver() {
		if (game.isLost())
			if (JOptionPane.showConfirmDialog(this,
					"Game over!", "You are dead!", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE) == 0) {
				game.stopGame();
			}
	}

	/* ========================================= UTILITIES ====================================== */

	private void addStaticEntity(Entity e) {
		statics.add(e);
	}

	private void addDynamicEntity(Entity e) {
		dynamics.add(e);
	}

	private void removeAllDynamicsOfType(EntityType type) {
		List<Entity> oftype = dynamics.stream().filter(e -> e.getType() == type).collect(Collectors.toList());
		dynamics.removeAll(oftype);
	}

	@Override
	protected void doUISetup() {
		panel.setLayout(null);
	}

	@Override
	public void setComponentActions(MainDisplay dsp) {}

	@Override
	public void redraw() {
		// check game state
		checkGameOver();
		// reset the component lists
		panel.removeAll();
		// do updates
		updateStaticEntities();
		updateDynamicEntities();
		updateBullets();
		// draw the lot
		for (Entity e : statics)
			draw(e.getImage(), e.getLocation().x, e.getLocation().y,
					e.getImage().getWidth(), e.getImage().getHeight());
		for (Entity e : dynamics)
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

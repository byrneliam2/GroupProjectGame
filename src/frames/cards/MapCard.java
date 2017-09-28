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
import npc.NPC;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The MapCard displays the state of a Map, including all Entities on screen.
 */
public class MapCard extends Card {

    private List<Entity> entities;
    private map.Map map;
    private IGame game;

    private static final int HEART_POS = 100;
    private static final int CONSOLE_FACTOR = 15;

    public MapCard(String n, map.Map map, IGame game) {
        super(n);
        this.entities = new ArrayList<>();
        this.map = map;
        this.game = game;

        setBackground(ImageUtilities.scale(
                ImageLoader.image("MapImages", map.getBackgroundLayer(), true),
                MainDisplay.WIDTH, MainDisplay.HEIGHT));

        addEntities();
    }

    /**
     * Add all entities to the map. This is done outside of the setup method since the map is not constructed
     * beforehand and also because this setup does not relate to Swing components.
     */
    private void addEntities() {
        // add player
        addEntity(new Entity(game.getPlayer(), EntityType.PLAYER,
                ImageLoader.image("ItemPictures", "key", true),
                new Point(game.getPlayer().getxLocation(), game.getPlayer().getyLocation()))
        );
        // add all NPCs
        map.getNPCS().forEach(npc -> addEntity(new Entity(
                npc, EntityType.NPC,
                ImageLoader.image("ItemPictures", "key", true),
                new Point(npc.getxLocation(), npc.getyLocation())))
        );
        // add all items
        map.getItems().forEach(item -> addEntity(new Entity(
                item, EntityType.ITEM,
                ImageLoader.image("ItemPictures", item.getImageFileName(), true),
                new Point(item.getX(), item.getY())))
        );
    }

    private void addUIEntities() {
        // add player health
        /*for (int i = 0; i < game.getPlayer().getHealth(); i++) {
            addEntity(new Entity(game.getPlayer(), EntityType.SPECIAL,
                    ImageLoader.image("game", "heart", true),
                    new Point((i+1) * HEART_POS, HEART_POS)));
        }*/
    }

    private void updateEntities() {
        for (Entity e : entities) {
            Object o = e.getObject();
            switch(e.getType()) {
                case ITEM:
                    Item i = (Item) o;
                    e.setLocation(new Point(i.getX(), i.getY()));
                    break;
                case PLAYER:
                    Player p = (Player) o;
                    e.setLocation(new Point(p.getxLocation(), p.getyLocation()));
                    break;
                case NPC:
                    NPC n = (NPC) o;
                    e.setLocation(new Point(n.getxLocation(), n.getyLocation()));
                    break;
            }
        }
    }

    /**
     * Add a new {@link Card.Entity} to the current screen.
     * @param e entity
     */
    private void addEntity(Entity e) {
        entities.add(e);
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
        updateEntities();
        // draw the lot
        for (Entity e : entities) {
            JLabel l = new JLabel(new ImageIcon(e.getImage()));
            panel.add(l);
            l.setBounds(e.getLocation().x, e.getLocation().y,
                    e.getImage().getWidth(), e.getImage().getHeight());
        }
    }
}

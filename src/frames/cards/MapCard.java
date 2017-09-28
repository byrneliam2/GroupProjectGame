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

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The MapCard displays the state of a Map, including all Entities on screen.
 */
public class MapCard extends Card {

    /* Swing attributes */
    private JTextArea console;

    /* Other attributes */
    private List<Entity> entities;
    private map.Map map;
    private IGame game;

    public MapCard(String n, map.Map map, IGame game) {
        super(n);
        this.entities = new ArrayList<>();
        this.map = map;
        this.game = game;

        this.console = new JTextArea(1, 1);

        setBackground(ImageUtilities.scale(
                ImageLoader.image("MapImages", map.getBackgroundLayer(), true),
                MainDisplay.WIDTH, MainDisplay.HEIGHT));

        addAllEntities();
    }

    /**
     * Add all entities to the map. This is done outside of the setup method since the map is not constructed
     * beforehand and also because this setup does not relate to Swing components.
     */
    private void addAllEntities() {
        // ================================ UI ENTITIES =====================================

        // add player health
        for (int i = 0; i < game.getPlayer().getHealth(); i++) {
            addEntity(new Entity(EntityType.SPECIAL,
                    ImageLoader.image("ui", "heart", true),
                    new Point((i+1) * 100, 100)));
        }

        // =============================== GAME ENTITIES =====================================

        // add player
        addEntity(new Entity(EntityType.PLAYER,
                ImageLoader.image("ItemPictures", "key", true),
                new Point(game.getPlayer().getxLocation(), game.getPlayer().getyLocation()))
        );
        // add all NPCs
        map.getNPCS().forEach(npc ->
                addEntity(new Entity(EntityType.NPC,
                        ImageLoader.image("ItemPictures", "key", true),
                        new Point(npc.getxLocation(), npc.getyLocation())))
        );
        // add all items
        map.getItems().forEach((item, point) ->
                addEntity(new Entity(
                        EntityType.ITEM,
                        ImageLoader.image("ItemPictures", item.getImageFileName(), true),
                        point))
        );
    }

    private void updateEntities() {
        for (Entity e : entities) {
            //
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
        entities.clear();
        // add all entities that exist on the screen still back
        addAllEntities();
        // draw the lot
        for (Entity e : entities) {
            JLabel l = new JLabel(new ImageIcon(e.getImage()));
            panel.add(l);
            l.setBounds(e.getLocation().x, e.getLocation().y,
                    e.getImage().getWidth(), e.getImage().getHeight());
        }
    }
}

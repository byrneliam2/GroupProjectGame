package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import gfx.ImageLoader;

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

    public MapCard(String n, map.Map map) {
        super(n);
        this.entities = new ArrayList<>();
        this.map = map;

        this.console = new JTextArea(1, 1);

        setBackground(ImageLoader.image("MapImages", map.getBackgroundLayer(), true));

        addAllEntities();
    }

    /**
     * Add all entities to the map. This is done outside of the setup method since the map is not constructed
     * beforehand and also because this setup does not relate to Swing components.
     */
    private void addAllEntities() {
        // add all items
        map.getItems().forEach((item, point) ->
                addEntity(new Entity(
                        ImageLoader.image("ItemPictures", item.getImageFileName(), true),
                        point)));
    }

    /**
     * Add a new {@link Card.Entity} to the current screen.
     * @param e entity
     */
    private void addEntity(Entity e) {
        entities.add(e);
    }

    @Override
    protected void doSetup() {
        panel.setLayout(new FlowLayout());
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {}

    @Override
    public void redraw() {
        for (Entity e : entities) {
            // draw entity at e.getLocation()
        }
    }
}

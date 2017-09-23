package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import gfx.ImageLoader;

import java.util.*;

/**
 * The MapCard displays the state of a Map, including all Entities on screen.
 */
public class MapCard extends Card {

    // TODO console screen for dialogue, part of this or main?

    private List<Entity> entities;
    private map.Map map;

    public MapCard(String n, map.Map map) {
        super(n);
        this.map = map;

        setBackground(ImageLoader.image("maps", map.getBackgroundLayer(), false));
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
        panel.setLayout(null);
        map.getItems().forEach((item, point) -> {
            addEntity(new Entity(ImageLoader.image("items", item.getFilePath(), true), point));
        });
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {

    }

    @Override
    public void redraw() {

    }
}

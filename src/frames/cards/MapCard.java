package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import java.util.List;

public class MapCard extends Card {

    private List<Entity> entities;

    /**
     * Add an entity to the current screen.
     * @param e entity
     */
    public void addEntity(Entity e) {
        entities.add(e);
    }

    @Override
    public void redraw() {

    }
}

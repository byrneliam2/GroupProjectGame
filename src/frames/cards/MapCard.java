package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import java.util.List;

/**
 * The MapCard displays the state of a Map, including all Entities on screen.
 */
public class MapCard extends Card {

    // TODO console screen for dialogue, part of this or main?

    private List<Entity> entities;

    /**
     * Add a new {@link Card.Entity} to the current screen.
     * @param e entity
     */
    public void addEntity(Entity e) {
        entities.add(e);
    }

    @Override
    protected void doSetup() {

    }

    @Override
    protected void setComponentActions() {

    }

    @Override
    public void redraw() {

    }
}

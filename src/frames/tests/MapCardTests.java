package frames.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.cards.MapCard;
import game.Game;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.swing.*;

import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapCardTests {

    @Test
    public void test01_MapData() {
        Game g = new Game();
        g.newGame();
        MapCard m = new MapCard("map", g.getWorld().getStartingMap(), g);
        assertNotNull(m.getComponentAt(
                SwingUtilities.convertPoint(
                        m.getComponent(0), g.getPlayer().getxLocation(), g.getPlayer().getyLocation(), m)));
    }

}

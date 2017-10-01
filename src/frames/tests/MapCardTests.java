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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapCardTests {

    @Test
    public void test01_MapData() {
        Game g = new Game();
        MapCard m = new MapCard("map", g.getWorld().getStartingMap(), g);
    }

}

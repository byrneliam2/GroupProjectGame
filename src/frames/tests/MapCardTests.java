package frames.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.utils.DisplayValues;
import frames.cards.MapCard;
import frames.cards.MenuCard;
import game.Game;
import map.World;
import common.game.IGame;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MapCardTests {

    @Test
    public void test01_MenuData() {
        MenuCard m = new MenuCard("menu");
        assertNotNull(m.getComponent(0).getComponentAt(DisplayValues.WIDTH/2, DisplayValues.HEIGHT/2 - 50));
    }

    @Test
    public void test02_MapData() {
        IGame g = new Game();
        g.newGame();
        MapCard m = new MapCard("map", g.getWorld().getStartingMap(), g);
        assertNotNull(m.getComponent(0).getComponentAt(g.getPlayer().getxLocation(), g.getPlayer().getyLocation()));
    }

    @Test
    public void test03_MapData() {
        IGame g = new Game();
        g.newGame();
        MapCard m = new MapCard("map", World.getMaps().get("Map3"), g);
        assertNotNull(m.getComponent(0).getComponentAt(g.getPlayer().getxLocation(), g.getPlayer().getyLocation()));
    }
}

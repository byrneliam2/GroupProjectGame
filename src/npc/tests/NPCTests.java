package npc.tests;

import npc.NPC;
import npc.PatrolScheme;
import org.junit.Test;
import player.Player;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NPCTests {








    /* EXTERNAL TESTING */

    /**
     * Check Bug Loses Health
     */
    @Test
    public void testTakeDamage(){
        Player p = new Player("", 20, 20);
        NPC n = new NPC("bug", 25, 25, 2, p, new PatrolScheme(true, 5));
        map.Map m = new map.Map("MapTest", p, null, new ArrayList() {{add(n);}}, new ArrayList<>());
        p.setMap(m);
        int health = n.getHealth();
        n.start();
        n.takeDamage();
        n.stop();
        assertTrue((health-1) == n.getHealth());
        assertFalse(n.isDead());
    }

    /**
     * Check Bug Dies
     */
    @Test
    public void testNPCDies(){
        Player p = new Player("", 20, 20);
        NPC n = new NPC("bug", 25, 25, 1, p, new PatrolScheme(true, 5));
        map.Map m = new map.Map("MapTest", p, null, new ArrayList() {{add(n);}}, new ArrayList<>());
        p.setMap(m);
        int health = n.getHealth();
        n.start();
        n.takeDamage();
        n.stop();
        assertTrue((health-1) == n.getHealth());
        assertTrue(n.isDead());
    }


}

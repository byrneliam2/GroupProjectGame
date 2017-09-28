package map.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import map.World;
import map.WorldParser;
import player.Player;

public class WorldTests {

	@Test
	public void startingMapNotLoaded() {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world2", p1);
		assert(w.getStartingMap()==null);
	}
	
	@Test
	public void CorrectAmountOfMaps() {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world2", p1);
		assert(World.getMaps().size()==1);
	}

}

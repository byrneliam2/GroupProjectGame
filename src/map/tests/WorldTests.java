package map.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import map.World;
import map.WorldParser;
import player.Player;

public class WorldTests {

	@Test
	public void correctStartingMap() {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world", p1);
		assert(w.getStartingMap()!=null);
		assert(w.getStartingMap().getName().equals("Map3"));
	}

	@Test
	public void CorrectAmountOfMaps() {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world", p1);
		assert(World.getMaps().size()==14);
	}

}

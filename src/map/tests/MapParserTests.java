package map.tests;

import java.io.IOException;

import org.junit.Test;

import map.BadMapImageException;
import map.ParseException;
import map.World;
import map.WorldParser;
import player.Player;

public class MapParserTests {

	@Test
	public void test() throws ParseException, BadMapImageException, IOException {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world",p1);
		p1.setMap(w.getStartingMap());
	}
	
	@Test(expected = ParseException.class)
	public void incorrectmapPathways() throws ParseException, BadMapImageException, IOException{
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("vfbsf",p1);
		p1.setMap(w.getStartingMap());
	}
	
	@Test
	public void incorectmapsize() throws ParseException, BadMapImageException, IOException {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world",p1);
		p1.setMap(w.getStartingMap());
	}
	
	@Test
	public void incorrectItemPlacement() throws ParseException, BadMapImageException, IOException {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world",p1);
		p1.setMap(w.getStartingMap());
	}

	@Test
	public void invalidItems() throws ParseException, BadMapImageException, IOException {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world",p1);
		p1.setMap(w.getStartingMap());
	}
}

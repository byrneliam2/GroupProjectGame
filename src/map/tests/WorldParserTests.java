package map.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import map.BadMapImageException;
import map.ParseException;
import map.World;
import map.WorldParser;
import player.Player;

public class WorldParserTests {

	@Test
	public void test() throws ParseException, BadMapImageException, IOException {
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("world",p1);
		p1.setMap(w.getStartingMap());
	}
	
	@Test
	public void incorrectmapPathways() throws ParseException, BadMapImageException, IOException{
		Player p1 = new Player("Tom", 50, 50);
		World w = WorldParser.parse("vfbsf",p1);
		//p1.setMap(w.getStartingMap());
	}
	

}

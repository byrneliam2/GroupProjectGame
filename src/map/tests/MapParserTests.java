package map.tests;

import java.io.IOException;

import org.junit.Test;

import map.BadMapImageException;
import map.Map;
import map.MapParser;
import map.ParseException;
import map.World;
import map.WorldParser;
import player.Player;

/**
 * @author James
 *
 */
public class MapParserTests {

	@Test
	public void testCorrectMaps() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map3", p1);
		assert (m != null);
	}

	@Test
	public void incorrectmapPathways() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("dssd", p1);
		assert (m == null);
	}

}

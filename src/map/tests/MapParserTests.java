package map.tests;

import org.junit.Test;

import common.map.IMap;
import common.player.IPlayer;
import map.MapParser;
import player.Player;

/**
 * @author James
 *
 */
public class MapParserTests {

	@Test
	public void testCorrectMaps() {
		IPlayer p1 = new Player("Tom", 50, 50);
		IMap m = MapParser.parse("Map3", p1);
		assert (m != null);
	}

	@Test
	public void incorrectmapPathways() {
		IPlayer p1 = new Player("Tom", 50, 50);
		IMap m = MapParser.parse("dssd", p1);
		assert (m == null);
	}

}

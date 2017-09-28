package map.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import map.BadMapImageException;
import map.Map;
import map.MapParser;
import player.Player;

public class MapTests {

	@Test
	public void testCanMove() throws BadMapImageException, IOException {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);

		assertFalse(m.canMove(0, 0));
		assertFalse(m.canMove(1, 1));
		assertFalse(m.canMove(5, 5));
		assertFalse(m.canMove(Map.tileSize - 1, Map.tileSize - 1));

	}

}

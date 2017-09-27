package map.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import map.BadMapImageException;
import map.Map;

public class MapTests {

	@Test
	public void testCanMove() throws BadMapImageException, IOException {
		Map m = new Map("map1", null, null, null, null);

		assertFalse(m.canMove(0, 0));
		assertFalse(m.canMove(1, 1));
		assertFalse(m.canMove(5, 5));
		assertFalse(m.canMove(Map.tileSize - 1, Map.tileSize - 1));
		assertTrue(m.canMove(Map.tileSize + 2, Map.tileSize + 2));
	}

}

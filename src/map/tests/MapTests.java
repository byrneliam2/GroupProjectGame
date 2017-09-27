package map.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import map.Map;

public class MapTests {

	@Test
	public void testCanMove() {
		Map m = new Map("map1", null, null, null);

		assertFalse(m.canMove(0, 0));
		assertFalse(m.canMove(1, 1));
		assertFalse(m.canMove(5, 5));
		assertFalse(m.canMove(Map.tileSize-1, Map.tileSize-1));
		assertTrue(m.canMove(Map.tileSize + 2, Map.tileSize + 2));
	}

}

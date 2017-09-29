package map.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import items.Item;
import items.itemList.HealthPot;
import map.BadMapImageException;
import map.Map;
import map.MapParser;
import player.Player;

public class MapTests {

	@Test
	public void testCanMove() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);

		assertFalse(m.canMove(-1, -1));
		assertFalse(m.canMove(0, 0));
		assertFalse(m.canMove(5, 5));
		assertFalse(m.canMove(Map.tileSize - 1, Map.tileSize - 1));
		assertTrue(m.canMove(Map.tileSize, Map.tileSize));
	}

	@Test
	public void testDropItemOnMap() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);
		Item i = new HealthPot();
		assertEquals(2, m.getItems().size());// map starts with two items.

		m.placeItem(i, 100, 100);

		assertEquals(3, m.getItems().size());
		assertEquals(100, m.getItems().get(2).getX());
		assertEquals(100, m.getItems().get(2).getY());
	}

	@Test
	public void testPickUpItemOnMap() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);
		Item i = new HealthPot();
		assertEquals(2, m.getItems().size());// map starts with two items.

		m.placeItem(i, 100, 100);

		assertEquals(3, m.getItems().size());
		assertEquals(100, m.getItems().get(2).getX());
		assertEquals(100, m.getItems().get(2).getY());
	}

}

package map.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import items.Item;
import items.itemList.HealthPot;
import items.itemList.MassiveGun;
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
		m.removeItem(i);
		assertEquals(2, m.getItems().size());// map goes back to two items
	}

	@Test
	public void testRepeatedDrop() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);
		Item i = new HealthPot();
		assertEquals(2, m.getItems().size());// map starts with two items.

		m.placeItem(i, 50, 50);
		assertEquals(3, m.getItems().size());
		assertEquals(50, m.getItems().get(2).getX());
		assertEquals(50, m.getItems().get(2).getY());
		m.removeItem(i);
		assertEquals(2, m.getItems().size());// map goes back to two items

		m.placeItem(i, 150, 150);
		assertEquals(3, m.getItems().size());
		assertEquals(150, m.getItems().get(2).getX());
		assertEquals(150, m.getItems().get(2).getY());
		m.removeItem(i);
		assertEquals(2, m.getItems().size());// map goes back to two items

	}

	/**
	 * Tests the range circle on item on centre of player.
	 */
	@Test
	public void testRangeCircle() {
		Player p1 = new Player("Tom", 50, 50);
		// centre of circle is at aproximatly 82,82.

		Map m = MapParser.parse("Map1", p1);
		Item i = new HealthPot();
		m.placeItem(i, (int) p1.getRangeCircle().getCenterX(), (int) p1.getRangeCircle().getCenterY());

		// tests item on top of the player
		assertEquals(i, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle on item at edge.
	 */
	@Test
	public void testRangeCircle2() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);
		Item i = new HealthPot();

		m.placeItem(i, 50 + Map.tileSize, 51);

		// tests item at very edge of range circle
		assertEquals(i, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a horizontal line.
	 */
	@Test
	public void testRangeCircle3() {
		Player p1 = new Player("Tom", 50, 50);
		Map m = MapParser.parse("Map1", p1);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, 50, 50 + Map.tileSize);
		m.placeItem(i2, 60, 50 + Map.tileSize);
		m.placeItem(i3, 55, 50 + Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}



}

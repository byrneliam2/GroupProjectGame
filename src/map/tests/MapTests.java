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
import player.InvalidPlayerExceptions;
import player.Player;

public class MapTests {

	private int x = 90, y = 90;
	private Player p1;
	private Map m;

	public void setup() {
		p1 = new Player("Tom", x, y);
		m = MapParser.parse("Map3", p1);
		p1.setMap(m);
	}

	@Test
	public void testCanMove() {
		setup();
		assertFalse(m.canMove(-1, -1));
		assertFalse(m.canMove(0, 0));
		assertFalse(m.canMove(5, 5));
		assertFalse(m.canMove(Map.tileSize - 1, Map.tileSize - 1));
		assertTrue(m.canMove(Map.tileSize, Map.tileSize));
	}

	@Test
	public void testDropItemOnMap() {
		setup();
		Item i = new HealthPot();
		assertEquals(1, m.getItems().size());// map starts with two items.

		m.placeItem(i, 100, 100);

		assertEquals(2, m.getItems().size());
		assertEquals(100, m.getItems().get(1).getX());
		assertEquals(100, m.getItems().get(1).getY());
	}

	@Test
	public void testPickUpItemOnMap() {
		setup();
		Item i = new HealthPot();
		assertEquals(1, m.getItems().size());// map starts with two items.

		m.placeItem(i, 100, 100);
		m.removeItem(i);
		assertEquals(1, m.getItems().size());// map goes back to two items
	}

	@Test
	public void testRepeatedDrop() {
		setup();
		Item i = new HealthPot();
		assertEquals(1, m.getItems().size());// map starts with two items.

		m.placeItem(i, x, y);
		assertEquals(2, m.getItems().size());
		assertEquals(x, m.getItems().get(1).getX());
		assertEquals(y, m.getItems().get(1).getY());
		m.removeItem(i);
		assertEquals(1, m.getItems().size());// map goes back to two items

		m.placeItem(i, 150, 150);
		assertEquals(2, m.getItems().size());
		assertEquals(150, m.getItems().get(1).getX());
		assertEquals(150, m.getItems().get(1).getY());
		m.removeItem(i);
		assertEquals(1, m.getItems().size());// map goes back to two items

	}

	/**
	 * Tests the range circle on item on centre of player.
	 */
	@Test
	public void testRangeCircle() {
		setup();
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
		setup();
		Item i = new HealthPot();

		m.placeItem(i, x + Map.tileSize, x + 1);

		// tests item at very edge of range circle
		assertEquals(i, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a horizontal line.
	 */
	@Test
	public void testRangeCircle3() {
		setup();
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, x, y + Map.tileSize);
		m.placeItem(i2, x + 10, y + Map.tileSize);
		m.placeItem(i3, x + 5, y + Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a Vertical line.
	 */
	@Test
	public void testRangeCircle4() {
		setup();
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, x + Map.tileSize, y);
		m.placeItem(i2, x + Map.tileSize, y + 10);
		m.placeItem(i3, x + Map.tileSize, y + 5);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle is still correct once player has moved
	 */
	@Test
	public void testRangeCircle5() {
		setup();
		Item i = new HealthPot();
		Item i2 = new MassiveGun();

		m.placeItem(i, x + Map.tileSize, y + 10);
		m.placeItem(i2, x + 1 + Map.tileSize, y + 10);

		// item i should be closest
		assertEquals(i, m.getClosestItem(p1.getRangeCircle()));

		try {
			p1.move(1, 0);
		} catch (InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
		// item i2 should be closest.
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}

}

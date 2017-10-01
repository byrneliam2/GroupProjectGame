package map.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import items.Item;
import items.itemList.HealthPot;
import items.itemList.MassiveGun;
import map.BadMapImageException;
import map.Environment;
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
		m = MapParser.parse("MapTest", p1);
		p1.setMap(m);
	}

	public void environmentCollisionSetup() {
		p1 = new Player("Tom", x, y);
		m = MapParser.parse("MapTest", p1);
		p1.setMap(m);
	}

	@Test
	public void enviromentTest() {
		this.environmentCollisionSetup();
		for (int i = 24; i <= 27; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnviromentTile(i * Map.tileSize, j).equals(Environment.DEATH));
			}
		}

		for (int i = 17; i <= 20; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnviromentTile(i * Map.tileSize, j).equals(Environment.FIRE));
			}
		}

		for (int i = 17; i <= 20; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnviromentTile(i * Map.tileSize, j).equals(Environment.FIRE));
			}
		}

		for (int i = 10; i <= 13; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnviromentTile(i * Map.tileSize, j).equals(Environment.MUD));
			}
		}

		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j < 32; j++) {
				if (i > 2) {
					assert (m.onEnviromentTile(i * Map.tileSize, j).equals(Environment.MIST));
				} else {
					assertNull(m.onEnviromentTile(i * Map.tileSize, j));
				}

			}
		}

	}

	@Test
	public void testCanMove() {
		this.environmentCollisionSetup();
		assertFalse(m.canMove(-1, -1));
		assertFalse(m.canMove(-1, 2));
		assertFalse(m.canMove(2, -1));
		assertTrue(m.canMove(5, 5));
		assertFalse(m.canMove(33 * Map.tileSize, 33 * Map.tileSize));
		assertFalse(m.canMove(33 * Map.tileSize, 5 * Map.tileSize));
		assertFalse(m.canMove(5 * Map.tileSize, 33 * Map.tileSize));

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 18; j++) {
				if (i >= 13 && i <= 15) {
					assertFalse(m.canMove(i * Map.tileSize, j * Map.tileSize));
				} else {
					assertTrue(m.canMove(i * Map.tileSize, j * Map.tileSize));
				}
			}

		}
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
	 * Tests the range circle on item on center of player.
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
		this.setup();
		Item i = new HealthPot();

		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a horizontal line.
	 */
	@Test
	public void testRangeCircle3() {
		p1 = new Player("Tom", 3 * Map.tileSize, 2 * Map.tileSize);
		m = MapParser.parse("MapTest", p1);
		p1.setMap(m);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i2, 3 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i3, 4 * Map.tileSize, 2 * Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a Vertical line.
	 */
	@Test
	public void testRangeCircle4() {
		p1 = new Player("Tom", (2 * Map.tileSize) + (Map.tileSize / 2) + 20, (3 * Map.tileSize) + Map.tileSize / 2);
		m = MapParser.parse("MapTest", p1);
		p1.setMap(m);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i2, 2 * Map.tileSize, 3 * Map.tileSize);

		m.placeItem(i3, 2 * Map.tileSize, 4 * Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}

	/**
	 * Tests the range circle is still correct once player has moved
	 */
	@Test
	public void testRangeCircle5() {
		p1 = new Player("Tom", 120, 130);
		m = MapParser.parse("MapTest", p1);
		p1.setMap(m);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i2, 2 * Map.tileSize, 3 * Map.tileSize);

		// item i should be closest
		assertEquals(i, m.getClosestItem(p1.getRangeCircle()));

		try {
			p1.move(0, 60);
		} catch (InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
		// item i2 should be closest.
		assertEquals(i2, m.getClosestItem(p1.getRangeCircle()));
	}

}

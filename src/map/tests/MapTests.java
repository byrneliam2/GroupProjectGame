package map.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.junit.Test;

import common.items.Item;
import common.map.Environment;
import common.map.IMap;
import common.map.IWorld;
import common.player.IPlayer;
import items.Key;
import items.itemList.HealthPot;
import items.itemList.MassiveGun;
import map.BadMapImageException;
import map.Map;
import map.MapParser;
import map.WorldParser;
import npc.NPC;
import npc.PatrolScheme;
import common.items.*;
import common.player.*;
import common.*;
import player.Bullet;
import player.InvalidPlayerExceptions;
import player.Player;

/**
 * @author James
 *
 */
public class MapTests {

	private int x = 90, y = 90;
	private IPlayer p1;
	private IMap m;
	private IMap m2;
	private IWorld w;

	public void setup() {
		p1 = new Player("Tom", x, y);
		m = MapParser.parse("MapTest", p1);
		p1.setMap((Map) m);
	}

	public void environmentCollisionSetup() {
		p1 = new Player("Tom", x, y);
		m = MapParser.parse("MapTest", p1);
		p1.setMap((Map) m);
	}

	public void doorSetup() {
		p1 = new Player("Tom", 120, 120);
		w = WorldParser.parse("worldTest", p1);
	}

	/**
	 * Test all types of environments
	 */
	@Test
	public void enviromentTest() {
		this.environmentCollisionSetup();
		for (int i = 24; i <= 27; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnvironmentTile(i * Map.tileSize, j).equals(Environment.DEATH));
			}
		}

		for (int i = 17; i <= 20; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnvironmentTile(i * Map.tileSize, j).equals(Environment.FIRE));
			}
		}

		for (int i = 17; i <= 20; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnvironmentTile(i * Map.tileSize, j).equals(Environment.FIRE));
			}
		}

		for (int i = 10; i <= 13; i++) {
			for (int j = 0; j < 32; j++) {
				assert (m.onEnvironmentTile(i * Map.tileSize, j).equals(Environment.MUD));
			}
		}

		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j < 32; j++) {
				if (i > 2) {
					assert (m.onEnvironmentTile(i * Map.tileSize, j).equals(Environment.MIST));
				} else {
					assertNull(m.onEnvironmentTile(i * Map.tileSize, j));
				}

			}
		}

	}

	/**
	 * Tests invalid positions for environments
	 */
	@Test
	public void enviromentTestInvalidPoints() {
		this.environmentCollisionSetup();
		assertNull(m.onEnvironmentTile(-1, 0));
		assertNull(m.onEnvironmentTile(0, -1));
		assertNull(m.onEnvironmentTile(-1, -1));

	}

	/**
	 * Tests that you can and can't move for fringe map positions
	 */
	@Test
	public void testCanMoveSpecificTiles() {
		this.environmentCollisionSetup();
		assertFalse(m.canMove(-1, -1));
		assertFalse(m.canMove(-1, 2));
		assertFalse(m.canMove(2, -1));
		assertTrue(m.canMove(5, 5));
		assertFalse(m.canMove(33 * Map.tileSize, 33 * Map.tileSize));
		assertFalse(m.canMove(33 * Map.tileSize, 5 * Map.tileSize));
		assertFalse(m.canMove(5 * Map.tileSize, 33 * Map.tileSize));

	}

	@Test
	public void testCanMoveAtEveryPoint() {
		this.environmentCollisionSetup();

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
	public void testCanMoveAtEveryPointRectangle() {
		this.environmentCollisionSetup();
		// Rectangle is 1 smaller than it should be so there are no overlaps
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 18; j++) {
				Rectangle.Double r = new Rectangle.Double(i * Map.tileSize, j * Map.tileSize, Map.tileSize - 1,
						Map.tileSize - 1);
				if (i >= 13 && i <= 15) {
					assertFalse(m.canMove(r));
				} else {
					assertTrue(m.canMove(r));
				}
			}

		}
	}

	@Test
	public void testCanMoveRectangleInvalidBottomLeft() {
		this.environmentCollisionSetup();

		// Rectangle with invalid bottom left
		Rectangle.Double rBL0 = new Rectangle.Double(-1, 300, 10, 10);
		assertFalse(m.canMove(rBL0));

	}

	@Test
	public void moveRectangleInvalidTopLeft() {
		this.environmentCollisionSetup();
		// Rectangle with invalid top left
		Rectangle.Double rTL0 = new Rectangle.Double(-1, 300, 10, 10);
		Rectangle.Double rTL1 = new Rectangle.Double(-1, -1, 10, 10);
		assertFalse(m.canMove(rTL0));
		assertFalse(m.canMove(rTL1));
	}

	@Test
	public void moveRectangleInvalidTopRight() {
		this.environmentCollisionSetup();
		// Rectangle with invalid top right
		Rectangle.Double rTR0 = new Rectangle.Double(300, 300, 32 * Map.tileSize, 10);
		Rectangle.Double rTR1 = new Rectangle.Double(Map.tileSize * 35, 300, 10, 10);
		assertFalse(m.canMove(rTR0));
		assertFalse(m.canMove(rTR1));

	}

	@Test
	public void moveRectangleInvalidBottomRight() {
		this.environmentCollisionSetup();
		// Rectangle with invalid bottom right
		Rectangle.Double rBR0 = new Rectangle.Double(300, 300, 10, 32 * Map.tileSize);
		Rectangle.Double rBR1 = new Rectangle.Double(300, 35 * Map.tileSize, 10, 10);
		assertFalse(m.canMove(rBR0));
		assertFalse(m.canMove(rBR1));
	}

	/**
	 * Test that you can move through a open door
	 * 
	 * @throws InvalidPlayerExceptions
	 */
	@Test
	public void doorTestOpen() throws InvalidPlayerExceptions {
		this.doorSetup();
		assert (w.getStartingMap().getName().equals("Map3Test"));

		Map curMap = w.getStartingMap();

		((Player) p1).setSpeed(1);

		assertEquals(150, p1.getCentreX());
		assertEquals(150, p1.getCentreY());

		p1.move(0, 120);
		assertEquals(150, p1.getCentreX());
		assertEquals(270, p1.getCentreY());

		assertTrue(p1.move(-100, 0));
		assertEquals("Map8", p1.getMap().getName());
	}

	/**
	 * Test that you can move through a door that is locked but you have the correct
	 * key
	 * 
	 * @throws InvalidPlayerExceptions
	 */
	@Test
	public void doorTestLockedWithKey() throws InvalidPlayerExceptions {
		// Player starts on Map3 and should pick up a key then move over the left door
		// and into Map8
		this.doorSetup();
		assert (w.getStartingMap().getName().equals("Map3Test"));

		Map curMap = w.getStartingMap();

		curMap.placeItem(new Key(01, "white"), 120, 180);
		Item key = null;
		for (Item i : curMap.getItems()) {
			if (i instanceof Key && i.getName().equals("whiteKey")) {
				key = i;
			}
		}
		assert (key != null);
		((Player) p1).setSpeed(1);

		assertEquals(150, p1.getCentreX());
		assertEquals(150, p1.getCentreY());

		assertEquals(120, key.getX());
		assertEquals(180, key.getY());

		p1.move(0, 30);
		assertEquals(150, p1.getCentreX());
		assertEquals(180, p1.getCentreY());
		p1.pickUpItem();
		p1.move(0, 35);
		assertEquals(215, p1.getCentreY());
		assertTrue(p1.move(-100, 0));
		assertEquals("Map8", p1.getMap().getName());
	}

	/**
	 * Tests that you cannot move though a locked door if you do not have the
	 * correct key
	 * 
	 * @throws InvalidPlayerExceptions
	 */
	@Test
	public void doorTestLockedWithoutKey() throws InvalidPlayerExceptions {
		// Player starts on Map3 and should not pick up a key then move over the left
		// door and not be able to move onto Map 8
		this.doorSetup();
		assert (w.getStartingMap().getName().equals("Map3Test"));

		Map curMap = w.getStartingMap();

		((Player) p1).setSpeed(1);

		assertEquals(150, p1.getCentreX());
		assertEquals(150, p1.getCentreY());

		p1.move(0, 30);
		assertEquals(150, p1.getCentreX());
		assertEquals(180, p1.getCentreY());
		p1.move(0, 35);
		assertEquals(215, p1.getCentreY());
		assertFalse(p1.move(-100, 0));
		assertEquals("Map3Test", p1.getMap().getName());
	}

	@Test
	public void testRemovingNpc() {
		this.doorSetup();
		assertEquals(2, w.getStartingMap().getNPCs().size());
		w.getStartingMap().removeNPC(w.getStartingMap().getNPCs().get(0));
		assertEquals(1, w.getStartingMap().getNPCs().size());
	}

	@Test
	public void testAddingNpc() {
		this.doorSetup();
		NPC c = new NPC("bug", 200, 200, 200, this.p1, new PatrolScheme(false, 5));
		assertEquals(2, w.getStartingMap().getNPCs().size());
		w.getStartingMap().getNPCs().add(c);
		assertEquals(3, w.getStartingMap().getNPCs().size());
	}

	@Test
	public void testStoppingStartingNpc() {
		// Works on the basis that stopping a NPC returns true if it is not already
		// stopped
		this.doorSetup();
		assertEquals(2, w.getStartingMap().getNPCs().size());
		NPC c = w.getStartingMap().getNPCs().get(0);
		w.getStartingMap().startMapNPCs();
		w.getStartingMap().pauseMapNPCs();
		assertFalse(c.stop());
		w.getStartingMap().startMapNPCs();
		assertTrue(c.stop());

	}

	@Test
	public void testNpcBulletHitting() throws InvalidPlayerExceptions, InterruptedException {
		// Bullet direction is 0-2Pie, 0 being north,pie being south,
		p1 = new Player("Tom", 370, 170);

		w = WorldParser.parse("world", p1);
		NPC c = w.getStartingMap().getNPCs().get(0);
		int health = p1.getHealth();
		assertEquals(400, p1.getCentreX());
		assertEquals(200, p1.getCentreY());
		Bullet b = new Bullet(400, 400, 0, c, 3, "npcBullet1");

		Thread.sleep(980);
		assert (400 == b.getX());

		assertEquals(health - 1, p1.getHealth());

	}

	@Test
	public void testPlayerBulletHitting() throws InvalidPlayerExceptions, InterruptedException {
		// Bullet direction is 0-2Pie, 0 being north,pie being south,
		this.doorSetup();
		NPC c = w.getStartingMap().getNPCs().get(0);
		c.start();
		c.stop();
		assertEquals(1182, c.getCentreX());
		assertEquals(958, c.getCentreY());
		Bullet b = new Bullet(1182, 940, 0, (Player) p1, 2, "playerBullet1");
		double bulletY = b.getY();
		int health = c.getHealth();

		Thread.sleep(3000);
		assert (b.getY() < bulletY);
		assertEquals(health - 1, c.getHealth());

	}

	@Test
	public void nullDoorTest() {
		this.doorSetup();
		assertNull(w.getStartingMap().getDoor(100));
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
	public void testBackgroundLayer() {
		this.doorSetup();
		assertEquals("Map3Test", w.getStartingMap().getName());

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
		m.placeItem(i, (int) ((Player) p1).getRangeCircle().getCenterX(),
				(int) ((Player) p1).getRangeCircle().getCenterY());

		// tests item on top of the player
		assertEquals(i, m.getClosestItem(((Player) p1).getRangeCircle()));
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
		assertEquals(i, m.getClosestItem(((Player) p1).getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a horizontal line.
	 */
	@Test
	public void testRangeCircle3() {
		p1 = new Player("Tom", 3 * Map.tileSize, 2 * Map.tileSize);
		m = MapParser.parse("MapTest", p1);
		p1.setMap((Map) m);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i2, 3 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i3, 4 * Map.tileSize, 2 * Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(((Player) p1).getRangeCircle()));
	}

	/**
	 * Tests the range circle on 3 items in a Vertical line.
	 */
	@Test
	public void testRangeCircle4() {
		p1 = new Player("Tom", (2 * Map.tileSize) + (Map.tileSize / 2) + 20, (3 * Map.tileSize) + Map.tileSize / 2);
		m = MapParser.parse("MapTest", p1);
		p1.setMap((Map) m);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		Item i3 = new HealthPot();

		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i2, 2 * Map.tileSize, 3 * Map.tileSize);

		m.placeItem(i3, 2 * Map.tileSize, 4 * Map.tileSize);

		// tests item at very edge of range circle
		assertEquals(i2, m.getClosestItem(((Player) p1).getRangeCircle()));
	}

	/**
	 * Tests the range circle is still correct once player has moved
	 */
	@Test
	public void testRangeCircle5() {
		p1 = new Player("Tom", 120, 130);
		m = MapParser.parse("MapTest", p1);
		System.out.println(p1.getCentreX());
		p1.setMap((Map) m);
		Item i = new HealthPot();
		Item i2 = new MassiveGun();
		m.placeItem(i, 2 * Map.tileSize, 2 * Map.tileSize);
		m.placeItem(i2, 2 * Map.tileSize, 3 * Map.tileSize);

		// item i should be closest
		assertEquals(i, m.getClosestItem(((Player) p1).getRangeCircle()));

		try {
			p1.move(0, 10);
		} catch (InvalidPlayerExceptions e) {
			e.printStackTrace();
			fail();
		}
		// item i2 should be closest.
		assertEquals(i2, m.getClosestItem(((Player) p1).getRangeCircle()));
	}

	/* EXTERNAL TESTING STARTS HERE */

	/**
	 * Tests if total the doors field == null, return null when trying to get a door
	 */
	@Test
	public void testNullDoors() {
		p1 = new Player("Tom", 120, 130);
		m = new Map("MapTest", p1, new ArrayList<>(), new ArrayList<>(), null);
		p1.setMap((Map) m);

		assertNull(m.getDoor(new Rectangle2D.Double(1, 1, 1, 1)));
	}

	/**
	 * Tests if total the doors field == null, return null when trying to get a door
	 */
	@Test
	public void testNullItems() {
		p1 = new Player("Tom", 120, 130);
		m = new Map("MapTest", p1, null, new ArrayList<>(), new ArrayList<>());
		p1.setMap((Map) m);

		assertNull(m.getClosestItem(new Ellipse2D.Double(1, 1, 1, 1)));
	}

	/**
	 * Tests if the Background Layer is Returned
	 */
	@Test
	public void testGetBackground() {
		p1 = new Player("Tom", 120, 130);
		m = MapParser.parse("MapTest", p1);
		p1.setMap((Map) m);

		assertEquals("MapTest", m.getBackgroundLayer());
	}

	/**
	 * Tests if the Background Layer is Returned
	 */
	@Test
	public void testThrowException() {
		try {
			throw new BadMapImageException("Test");
		} catch (BadMapImageException e) {
			assertEquals("Test", e.getMessage());
		}
	}

	/**
	 * Tests Environment Enum
	 */
	@Test
	public void getEnvironmentCode() {
		assertTrue(1 == Environment.DEATH.getEnviromentCode());
		assertTrue(2 == Environment.MIST.getEnviromentCode());
		assertTrue(3 == Environment.FIRE.getEnviromentCode());
		assertTrue(4 == Environment.MUD.getEnviromentCode());
	}

}

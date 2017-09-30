package save_load.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Game;
import save_load.SaveLoad;

public class allTests {

	@Test
	public void testSaving() {
		Game g = new Game();
		SaveLoad.saveGame(g);
	}

	@Test
	public void loadGame() {

	}

}

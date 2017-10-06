package save_load;

import game.Game;
import game.IGame;
import map.World;
import player.InvalidPlayerExceptions;
import player.Player;
import common.Direction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observer;

public class SaveLoad implements IGame, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SaveLoad(Game g) {

		saveGame(g);
	}

	public static void main(String[] args) {
		Game newGame = new Game();
		saveGame(newGame);
	}

	public static void saveGame(Game g) {
		/*
		 * This method will take any Object as a parameter, so as long as the class
		 * implements the serializable interface.
		 */
		FileOutputStream theSavedGame = null;
		try {
			theSavedGame = new FileOutputStream("saveGame");
			ObjectOutputStream theByteCode = new ObjectOutputStream(theSavedGame);
			theByteCode.writeObject(g);
			theByteCode.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Game loadGame(File f) {
		/*
		 * if(loadKeyPresses){ make a
		 *
		 *
		 *
		 * }
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 */

		return null;
	}

	@Override
	public void giveObserver(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCurrentMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pauseGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unPauseGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void movePlayer(Direction dir) throws InvalidPlayerExceptions {
		// TODO Auto-generated method stub

	}

	@Override
	public void interact() throws InvalidPlayerExceptions {
		// TODO Auto-generated method stub

	}

	@Override
	public void shoot(double x, double y) throws InvalidPlayerExceptions {
		// TODO Auto-generated method stub

	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isOver() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub

	}

}

package save_load;

import game.Game;
import common.game.IGame;
import map.World;
import npc.NPC;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.Direction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Observer;

import javax.tools.JavaFileManager.Location;

/*
 *  Game Save and Load
 */

public class SaveLoad implements IGame, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static String thePath;

	public SaveLoad() {

	}

	public SaveLoad(Game game, String thePath) {
		this.thePath = thePath;
	}

	public static void main(String[] args) {
		Game newGame = new Game();
		saveGame(newGame, thePath);
	}

	public static void saveGame(Game g, String theFilePath) {
		System.out.println("THIS IS ACTUALLY THE SAVING FUNCTION" + g);
		/*
		 * This method will take any Object as a parameter, so as long as the class
		 * implements the serializable interface.
		 */
		// File thePathFile = new File("./GroupProject/src/assets/saveGames");
		FileOutputStream theSavedGame = null;
		String savePath = theFilePath.concat(".dat");
		try {
			if (theFilePath != null) {
				theSavedGame = new FileOutputStream(savePath);
				ObjectOutputStream theByteCode = new ObjectOutputStream(theSavedGame);
				theByteCode.writeObject(g);
				theByteCode.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Game loadGame(String s) {

		System.out.println("THIS IS ACTUALLY THE loadGame FUNCTION" + s);
		Game game = null;

		if (s.contains(".dat")) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(s));
				game = (Game) ois.readObject();
				ois.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return game;
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
	public void saveGame(String filePath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
	}

	@Override
	public void specialAbility(double x, double y) throws InvalidPlayerExceptions {
		// TODO Auto-generated method stub

	}

}

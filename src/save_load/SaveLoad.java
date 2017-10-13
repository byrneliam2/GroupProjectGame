package save_load;

import game.Game;
import common.game.IGame;
import map.World;
import npc.NPC;
import player.InvalidPlayerExceptions;
import player.Player;
import common.utils.Direction;

import java.io.File;
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

public class SaveLoad {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static String thePath;

	public SaveLoad() {

	}

	public SaveLoad(String thePath) {
		this.thePath = thePath;
	}

	public SaveLoad(Game game, String thePath) {
		this.thePath = thePath;
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

	public static Game loadGame(File fileToLoad) {

		System.out.println("THIS IS ACTUALLY THE loadGame FUNCTION");
		Game game = null;


			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToLoad));
				game = (Game) ois.readObject();
				ois.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return game;
	}
}

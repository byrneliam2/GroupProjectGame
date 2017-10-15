package save_load;

import game.Game;

import java.io.*;

/**
 *  Game Save and Load
 *  
 *  @author Mohsen
 */
public class SaveLoad {

	/**
	 * Saves the game using serialization.
	 * 
	 * @param g
	 *            the game to be saved.
	 * @param theFilePath
	 *            the file path of the file to save it as.
	 */
	public static void saveGame(Game g, String theFilePath) {

		FileOutputStream theSavedGame = null;
		if (theFilePath.endsWith(".dat"))//all saved games should end with .dat
			theFilePath = theFilePath.concat(".dat");
		
		try {
			if (theFilePath != null) {
				theSavedGame = new FileOutputStream(theFilePath);
				ObjectOutputStream theByteCode = new ObjectOutputStream(theSavedGame);
				theByteCode.writeObject(g);
				theByteCode.close();
			}
		} catch (IOException e) {
			System.out.println("Saving Failed");
			e.printStackTrace();
		}
	}

	/**
	 * @param fileToLoad
	 *            the file to load.
	 * @return the game loaded from the file
	 */
	public static Game loadGame(File fileToLoad) {
		Game game = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToLoad));
			game = (Game) ois.readObject();
			ois.close();
		} catch (Exception ex) {
			System.out.println("Loading Failed");
			ex.printStackTrace();
		}
		return game;
	}
}

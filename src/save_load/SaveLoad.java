package save_load;

import game.Game;

import java.io.*;

/*
 *  Game Save and Load
 */

public class SaveLoad {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


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

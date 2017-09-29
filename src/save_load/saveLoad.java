package save_load;

import game.Game;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class saveLoad {


	public static void main(String[] args) {
		Game newGame = new Game();
		saveGame(newGame);
	}

	public static void saveGame(Game g) {
		/*
		 * This method will take any Object as a parameter, so as long as
		 * the class implements the serializable interface.
		 * */
		FileOutputStream theSavedGame = null;
		try {
			theSavedGame = new FileOutputStream("TheFirstSave");
			ObjectOutputStream theByteCode = new ObjectOutputStream(theSavedGame);
			theByteCode.writeObject(g);
			theByteCode.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Game loadGame(File f) {
		/*
		 * if(loadKeyPresses){
		 * 		make a
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
		 * */

		return null;
	}

}

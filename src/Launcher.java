import audio.AudioHandler;
import controller.Controller;
import frames.MainDisplay;
import game.Game;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        Game g = new Game();
        SwingUtilities.invokeLater(() -> new MainDisplay(g, new AudioHandler(), new Controller(g)));
    }
}

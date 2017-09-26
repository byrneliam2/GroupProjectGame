import frames.MainDisplay;
import game.Game;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainDisplay(new Game()));
    }
}

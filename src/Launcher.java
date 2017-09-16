import frames.MainDisplay;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainDisplay::new);
    }
}

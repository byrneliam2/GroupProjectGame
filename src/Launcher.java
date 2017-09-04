import frame.MainFrame;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

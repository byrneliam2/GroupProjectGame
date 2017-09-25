package frames.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.swing.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FramesTests {

    private void killIn3Sec() throws InterruptedException {
        SwingUtilities.invokeLater(()->{
            MainDisplay m = new MainDisplay();
            new Timer(3000, e -> m.dispose()).start();
        });
        Thread.sleep(3000);
    }

    /* ======================================================== */

    @Test
    public void test01_ATest() {

    }
}

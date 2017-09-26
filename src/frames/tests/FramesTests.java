package frames.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import game.Game;
import game.MockGame;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.swing.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FramesTests {

    private void killIn3Sec(MainDisplay m) throws InterruptedException {
        SwingUtilities.invokeLater(()-> new Timer(3000, e -> m.dispose()).start());
        Thread.sleep(3000);
    }

    /* ======================================================== */

    @Test
    public void test01_LaunchNoExceptions() {
        try {
            MainDisplay m = new MainDisplay(new MockGame());
            m.enableInputMethods(false);
            m.update(null, null);
            killIn3Sec(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02_FECardDisplay() {
        try {
            MainDisplay m = new MainDisplay(new MockGame());
            m.enableInputMethods(false);
            m.update(null, "pause");
            killIn3Sec(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03_BECardDisplay() {
        try {
            MainDisplay m = new MainDisplay(new Game());
            m.enableInputMethods(false);
            m.update(null, "Map3");
            killIn3Sec(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXXX_DisplayingMapData() {

    }
}

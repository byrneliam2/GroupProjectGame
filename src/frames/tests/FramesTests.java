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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.swing.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FramesTests {

    private void killIn3Sec(MainDisplay m) throws InterruptedException {
        SwingUtilities.invokeLater(()-> new Timer(3000, e -> m.dispose()).start());
        Thread.sleep(3000);
    }

    /* ============================================================================== */

    // Note the usage of MockGame within these tests.
    // Where the details of the game are not required, the MockGame is used.
    // The Game class is only used where required.

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
    public void test02_PauseCardDisplay() {
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
    public void test03_SettingsCardDisplay() {
        try {
            MainDisplay m = new MainDisplay(new MockGame());
            m.enableInputMethods(false);
            m.update(null, "settings");
            killIn3Sec(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04_MapCardDisplay() {
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
    public void test05_ScreenHistory() {
        MainDisplay m = new MainDisplay(new MockGame());
        m.enableInputMethods(false);
        m.update(null, "pause");
        m.update(null, "last");
    }

    @Test
    public void test06_DisplayingMapData() {
        MainDisplay m = new MainDisplay(new Game());
        m.enableInputMethods(false);
        m.update(null, "Map3");
        // TODO check item positions
    }

    @Test
    public void test07_Timer() {
        MainDisplay m = new MainDisplay(new Game());
        m.start();
        assertTrue(m.getTimer().isRunning());
        m.stop();
        assertFalse(m.getTimer().isRunning());
    }
}

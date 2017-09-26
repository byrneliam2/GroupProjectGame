package controller.tests;

import controller.Controller;
import controller.Controller.KeyboardCommands;
import controller.MousePosition;
import game.MockGame;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import player.InvalidPlayerExceptions;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTests {

    //KeyboardCommands.class Tests

    /**
     * Testing Getter methods on keybinds
     */

    @Test
    public void test01_getKeybind_UP(){
        assertEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_UP);
    }

    @Test
    public void test02_getKeybind_DOWN(){
        assertEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_DOWN);
    }

    @Test
    public void test03_getKeybind_LEFT(){
        assertEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_LEFT);
    }

    @Test
    public void test04_getKeybind_RIGHT(){
        assertEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_RIGHT);
    }

    /**
     * Testing the Setting of Keybinds
     */

    @Test
    public void test05_setKeybind_UP(){
        assertEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_UP);
        KeyboardCommands.KEY_UP.setKeybind(KeyEvent.VK_W);
        assertNotEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_UP);
        assertEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_W);
    }

    @Test
    public void test06_setKeybind_DOWN(){
        assertEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_DOWN);
        KeyboardCommands.KEY_DOWN.setKeybind(KeyEvent.VK_S);
        assertNotEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_DOWN);
        assertEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_S);
    }

    @Test
    public void test07_setKeybind_LEFT(){
        assertEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_LEFT);
        KeyboardCommands.KEY_LEFT.setKeybind(KeyEvent.VK_A);
        assertNotEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_LEFT);
        assertEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_A);
    }

    @Test
    public void test08_setKeybind_RIGHT(){
        assertEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_RIGHT);
        KeyboardCommands.KEY_RIGHT.setKeybind(KeyEvent.VK_D);
        assertNotEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_RIGHT);
        assertEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_D);
    }

    //MousePosition.class Tests

    /**
     * Testing Getter Methods for Mouse Position
     */

    @Test
    public void test09_initMouse(){
        MousePosition mouse = new MousePosition();
        assertTrue(mouse.getX() == 0);
        assertTrue(mouse.getY() == 0);
    }

    @Test
    public void test10_settingMousePosition(){
        MousePosition mouse = new MousePosition();
        MouseEvent e = new MouseEvent(new JComponent(){}, 0, 0, 0, 100, 100, 0, false);
        mouse.mouseMoved(e);
        assertTrue(mouse.getX() == 100);
        assertTrue(mouse.getY() == 100);
    }

    @Test
    public void test11_settingMousePosition(){
        MousePosition mouse = new MousePosition();
        MouseEvent e = new MouseEvent(new JComponent(){}, 0, 0, 0, 500, 100, 0, false);
        mouse.mouseMoved(e);
        assertTrue(mouse.getX() == 500);
        assertTrue(mouse.getY() == 100);
    }

    //Controller.class Tests

    /**
     * Testing if an input will result in a response from the Model
     */

    @Test
    public void test12_moveUp() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return (dx == 0 && dy == -1); }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_W, true));
    }

    @Test
    public void test13_moveDown() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return (dx == 0 && dy == 1); }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_S, true));
    }

    @Test
    public void test14_moveLeft() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return (dx == -1 && dy == 0); }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_A, true));
    }

    @Test
    public void test15_moveRight() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return (dx == 1 && dy == 0); }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_D, true));
    }




}

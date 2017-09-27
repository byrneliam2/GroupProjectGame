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
     * Testing Keybinds getters and Setters
     */

    @Test
    public void test01_keyUp(){
        //Getter
        assertEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_UP);
        //Setter
        KeyboardCommands.KEY_UP.setKeybind(KeyEvent.VK_W);
        assertEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_W);
        //Reset
        KeyboardCommands.KEY_UP.resetKeybind();
        assertEquals(KeyboardCommands.KEY_UP.getKeybind(), KeyEvent.VK_UP);
    }

    @Test
    public void test02_keyDown(){
        //Getter
        assertEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_DOWN);
        //Setter
        KeyboardCommands.KEY_DOWN.setKeybind(KeyEvent.VK_W);
        assertEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_W);
        //Reset
        KeyboardCommands.KEY_DOWN.resetKeybind();
        assertEquals(KeyboardCommands.KEY_DOWN.getKeybind(), KeyEvent.VK_DOWN);
    }

    @Test
    public void test03_keyLeft(){
        //Getter
        assertEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_LEFT);
        //Setter
        KeyboardCommands.KEY_LEFT.setKeybind(KeyEvent.VK_W);
        assertEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_W);
        //Reset
        KeyboardCommands.KEY_LEFT.resetKeybind();
        assertEquals(KeyboardCommands.KEY_LEFT.getKeybind(), KeyEvent.VK_LEFT);
    }

    @Test
    public void test04_keyRight(){
        //Getter
        assertEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_RIGHT);
        //Setter
        KeyboardCommands.KEY_RIGHT.setKeybind(KeyEvent.VK_W);
        assertEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_W);
        //Reset
        KeyboardCommands.KEY_RIGHT.resetKeybind();
        assertEquals(KeyboardCommands.KEY_RIGHT.getKeybind(), KeyEvent.VK_RIGHT);
    }

    //MousePosition.class Tests

    /**
     * Testing Getter Methods for Mouse Position
     */

    @Test
    public void test05_initMouse(){
        MousePosition mouse = new MousePosition();
        assertTrue(mouse.getX() == 0);
        assertTrue(mouse.getY() == 0);
    }

    @Test
    public void test06_settingMousePosition(){
        MousePosition mouse = new MousePosition();
        MouseEvent e = new MouseEvent(new JComponent(){}, 0, 0, 0, 100, 100, 0, false);
        mouse.mouseMoved(e);
        assertTrue(mouse.getX() == 100);
        assertTrue(mouse.getY() == 100);
    }

    @Test
    public void test07_settingMousePosition(){
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
    public void test08_moveUp() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return dy < 0; }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_UP, true));
    }

    @Test
    public void test09_moveDown() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return dy > 0; }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_DOWN, true));
    }

    @Test
    public void test10_moveLeft() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return dx < 0; }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_LEFT, true));
    }

    @Test
    public void test11_moveRight() {
        MousePosition mouse = new MousePosition();
        MockGame game = new MockGame() {
            public boolean movePlayer(int dx, int dy) throws InvalidPlayerExceptions { return dx > 1; }
        };
        Controller c = new Controller(game, mouse);
        assertTrue(c.processInput(KeyEvent.VK_RIGHT, true));
    }




}

package common.mocks;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import common.controller.IController;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class MockController implements IController {
    @Override
    public void update() {

    }

    @Override
    public void reloadController() {

    }

    @Override
    public KeyAdapter getKeyAdapter() {
        return null;
    }

    @Override
    public MouseAdapter getMouseAdapter() {
        return null;
    }
}

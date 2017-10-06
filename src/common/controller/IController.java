package common.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public interface IController {
    /**
     * This method is used to invoke all the commands currently being 'pressed' in the Controller.
     */
    void update();

    /**
     * This method is used to reload the controller at any given time, if the implementor uses a list
     * of current keys pressed, this could be used to clear that list of commands.
     */
    void reloadController();

    /**
     *
     */
    KeyAdapter getKeyAdapter();

    /**
     *
     */
    MouseAdapter getMouseAdapter();
}

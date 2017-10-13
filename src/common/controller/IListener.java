package common.controller;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: Mohsen Javaher
 */

//TODO: Fix Controller Reference

import controller.Controller;

public interface IListener {

    /**
     * Notifies the Controller of an Event Triggering.
     * @param input The key/click with an event change
     * @param pressed If the key/mouse was Clicked or Released.
     */
    default void notifyController(Integer input, InputType type, Controller parent, boolean pressed){
        for(Command cmd : Command.values()) {
            if(!cmd.getType().equals(type)) continue;
            else if(!cmd.getValue().equals(input)) continue;
            parent.notifyCommands(cmd, pressed);
        }
    }
}

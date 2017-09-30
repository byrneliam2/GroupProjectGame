package controller;

import controller.enums.Command;
import controller.enums.InputType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
    private Controller parent;

    @Override
    public void keyPressed(KeyEvent e) {
        notifyController(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notifyController(e.getKeyCode(), false);
    }

    private void notifyController(Integer input, boolean pressed){
        for(Command cmd : Command.values()) {
            if(!cmd.getType().equals(InputType.KEYBOARD)) continue;
            else if(!cmd.getValue().equals(input)) continue;
            parent.notifyCommands(cmd, pressed);
        }
    }

    void setController(Controller parent){
        this.parent = parent;
    }
}

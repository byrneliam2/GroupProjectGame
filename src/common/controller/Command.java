package common.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public enum Command {
    /* Movement Commands */
    MOVE_UP(InputType.KEYBOARD, KeyEvent.VK_W),
    MOVE_LEFT(InputType.KEYBOARD, KeyEvent.VK_A),
    MOVE_DOWN(InputType.KEYBOARD, KeyEvent.VK_S),
    MOVE_RIGHT(InputType.KEYBOARD, KeyEvent.VK_D),

    /* Interact Commands */
    INTERACT(InputType.KEYBOARD, KeyEvent.VK_F),
    PRIMARY_ATTACK(InputType.MOUSE, MouseEvent.BUTTON1),
    SECONDARY_ATTACK(InputType.MOUSE, MouseEvent.BUTTON2),

    /* Game Commands */
    PAUSE(InputType.KEYBOARD, KeyEvent.VK_ESCAPE);

    //Fields
    private InputType type;
    private Integer value;

    Command(InputType type, Integer value) {
        this.setBinding(type, value);
    }

    public void setBinding(InputType type, Integer value){
        this.type = type;
        this.value = value;
    }

    public InputType getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }
}

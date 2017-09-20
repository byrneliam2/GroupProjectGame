package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import gfx.ImageLoader;

/**
 * The SettingsCard is a screen that hosts options for settings in the game,
 * such as audio adjustment...
 */
public class SettingsCard extends Card {

    public SettingsCard() {
        setBackground(ImageLoader.image("ui", "pause", false));
    }

    @Override
    protected void doSetup() {

    }

    @Override
    public void setComponentActions(MainDisplay dsp) {

    }

    @Override
    public void redraw() {

    }
}

package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.MainDisplay;
import gfx.GraphicsUtilities;
import gfx.ImageLoader;

import javax.swing.*;
import java.util.Map;

/**
 * The SettingsCard is a screen that hosts options for settings in the game,
 * such as audio and visual adjustments. The SettingsCard is slightly different
 * in the way that the card that calls it can vary (e.g: the call could come
 * from the pause menu or the main menu.) The MainDisplay class takes care of the
 * memory aspect; this class notifies MainDisplay of a switch to the previous screen
 * through the observer update mechanism. That is, the "Back" button escapes to the
 * "last" screen on the display.
 */
public class SettingsCard extends Card {

    public SettingsCard(String n) {
        super(n);
        setBackground(ImageLoader.image("ui", "pause", false));
    }

    @Override
    protected void doSetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // back button
        components.put("back", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_back", true),
                ImageLoader.image("ui", "bu_back_r", false), 0.5f));
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
            if (!(m.getValue() instanceof JButton)) continue;
            final String str = (String) m.getKey();
            final JButton btn = (JButton) m.getValue();
            btn.addActionListener(e -> {
                switch (str) {
                    case "back":
                        // switch to last screen
                        dsp.update(null, "last");
                        break;
                }
            });
        }
    }

    @Override
    public void redraw() {
        panel.removeAll();
        // add the components in a top to bottom order, adding glue where we want space
        panel.add(Box.createVerticalGlue());
        panel.add(components.get("back"));
        panel.add(Box.createVerticalGlue());
    }
}

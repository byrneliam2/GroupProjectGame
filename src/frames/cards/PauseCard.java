package frames.cards;

import frames.MainDisplay;
import gfx.GraphicsUtilities;
import gfx.ImageLoader;

import javax.swing.*;
import java.util.Map;

public class PauseCard extends Card {

    public PauseCard(String n) {
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
        // save button
        components.put("save", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_save", true),
                ImageLoader.image("ui", "bu_save_r", false), 0.5f));
        // load button
        components.put("load", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_load", true),
                ImageLoader.image("ui", "bu_load_r", false), 0.5f));
        // settings button
        components.put("settings", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_settings", true),
                ImageLoader.image("ui", "bu_settings_r", false), 0.5f));
        // exit button
        components.put("exit", GraphicsUtilities.produceButton(
                ImageLoader.image("ui", "bu_exit", true),
                ImageLoader.image("ui", "bu_exit_r", false), 0.5f));
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
            if (!(m.getValue() instanceof JButton)) continue;
            final String str = (String) m.getKey();
            final JButton btn = (JButton) m.getValue();
            btn.addActionListener(e -> {
                switch (str) {
                    case "settings":
                        dsp.update(null, "settings");
                        break;
                    case "exit":
                        dsp.update(null, "menu");
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
        panel.add(components.get("save"));
        panel.add(components.get("load"));
        panel.add(components.get("settings"));
        panel.add(components.get("exit"));
        panel.add(Box.createVerticalGlue());
    }
}

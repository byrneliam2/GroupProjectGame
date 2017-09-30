package frames.cards;

import audio.tracks.SoundTrack;
import frames.MainDisplay;
import gfx.GraphicsUtilities;
import gfx.ImageLoader;

import javax.swing.*;
import java.io.File;
import java.util.Map;

public class PauseCard extends Card {

    public PauseCard(String n) {
        super(n);
        setBackground(ImageLoader.image("ui", "pause", false));
    }

    @Override
    protected void doUISetup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        // use a loop to load all the buttons into the list
        String[] files = {"back", "save", "load", "settings", "exit"};
        for (String s : files) {
            components.put(s, GraphicsUtilities.produceButton(
                    ImageLoader.image("ui", "bu_" + s, true),
                    ImageLoader.image("ui", "bu_" + s + "_r", false), 0.5f));
        }
    }

    @Override
    public void setComponentActions(MainDisplay dsp) {
        for (Map.Entry m : components.entrySet()) {
            if (!(m.getValue() instanceof JButton)) continue;
            final String str = (String) m.getKey();
            final JButton btn = (JButton) m.getValue();
            btn.addActionListener(e -> {
                dsp.getAudioHandler().playSound(SoundTrack.CLICK);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(".."));
                fileChooser.setDialogTitle("Select a <ext> file to load");
                switch (str) {
                    case "back":
                        break;
                    case "save":
                        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {}
                        break;
                    case "load":
                        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {}
                        break;
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

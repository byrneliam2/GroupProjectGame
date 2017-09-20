package frames.cards;

import frames.MainDisplay;
import gfx.ImageLoader;

public class PauseCard extends Card {

    public PauseCard() {
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

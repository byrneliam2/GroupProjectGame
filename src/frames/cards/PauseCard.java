package frames.cards;

import frames.MainDisplay;
import gfx.ImageLoader;

public class PauseCard extends Card {

    public PauseCard() {
        setBackground(ImageLoader.image("pause.jpg"));
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

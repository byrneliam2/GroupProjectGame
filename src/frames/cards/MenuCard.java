package frames.cards;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;

public class MenuCard extends Card {

    public MenuCard() {
        setBackground(ImageLoader.image("menu.jpg"));
    }

    @Override
    public void redraw() {
        /*Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(background, 0, 0, null);*/

        update();
    }
}

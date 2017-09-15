package gfx;

/*
 * Liam: 300338518
 * 4/09/2017
 * SWEN222PROJECT
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class is responsible for all image loading within the
 * game.
 */
public class ImageLoader {

    /**
     * Returns the image specified by the integer parameter.
     * @return BufferedImage version of the image specified
     */
    public static BufferedImage image(String path) {
        BufferedImage img = null;
        // FIXME currently retrieves images only from this directory
        try {
            img = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) { e.printStackTrace(); }

        return img;
    }
}

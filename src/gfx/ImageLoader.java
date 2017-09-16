package gfx;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for all image loading within the
 * game.
 */
public class ImageLoader {

    /**
     * Returns the image specified by the integer parameter.
     * @param path relative file path to image
     * @return BufferedImage version of the image specified
     */
    public static BufferedImage image(String path) {
        BufferedImage img = null;
        try {
            //img = ImageIO.read(ImageLoader.class.getResource(path));
            img = ImageIO.read(new File("img/" + path));
        } catch (IOException e) { e.printStackTrace(); }

        return img;
    }
}

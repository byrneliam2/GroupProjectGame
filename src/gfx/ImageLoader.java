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
 * This class is responsible for all image loading within the game. The images
 * come from the img directory located within the gfx library.
 */
public class ImageLoader {

    /**
     * Returns the image specified by the integer parameter.
     * @param path file path to image
     * @return BufferedImage version of the image specified
     */
    public static BufferedImage image(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(ImageLoader.class.getResource("img/" + path));
            //img = ImageIO.read(new File("img/" + path));
        } catch (IOException e) { e.printStackTrace(); }

        return img;
    }
}

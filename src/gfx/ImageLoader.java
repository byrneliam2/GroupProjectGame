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
 * come from the img directory in assets, with the subdirectory specified in the getter.
 * Currently supported files include JPEG and PNG.
 */
public class ImageLoader {

    /**
     * Returns the image specified by the integer parameter.
     * @param directory name of directory within the img folder
     * @param name file name
     * @param transparent is the image transparent? (this chooses a .jpg or .png format depending)
     * @return BufferedImage version of the image specified
     */
    public static BufferedImage image(String directory, String name, boolean transparent) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(ImageLoader.class.getResource(
                    "../assets/img/" + directory + (!directory.equals("") ? "/" : "") + name +
                            (transparent ? ".png" : ".jpg")
            ));
        } catch (IOException e) { e.printStackTrace(); }

        return img;
    }
}

package gfx;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * The ImageUtilities class hosts a variety of methods to manipulate images,
 * including rotation...
 */
public class ImageUtilities {

    /**
     * Rotate the given image by a set number of degrees.
     * @param img image to be rotated (note that this method produces a copy)
     * @param deg number of degrees to rotate
     * @return rotated copy of img
     */
    public static BufferedImage rotate(BufferedImage img, int deg) {
        AffineTransform af = AffineTransform.getRotateInstance(
                Math.toRadians(deg), img.getWidth()/2, img.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(af, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(img, null);
    }

    //
}

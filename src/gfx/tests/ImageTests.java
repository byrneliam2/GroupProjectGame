package gfx.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;
import gfx.ImageUtilities;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.image.BufferedImage;

import static junit.framework.TestCase.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageTests {

    @Test
    public void test01_Rotation() {
        BufferedImage img = ImageLoader.image("ui", "logo", true);
        BufferedImage rot90 = ImageUtilities.rotate(img, 90);
        BufferedImage rotated = ImageUtilities.rotate(rot90, 270);
        /*byte[] arrRot = ((DataBufferByte) rotated.getData().getDataBuffer()).getData();
        byte[] arrImg = ((DataBufferByte) img.getData().getDataBuffer()).getData();
        assertArrayEquals(arrRot, arrImg);*/
        for (int i = 0; i < img.getHeight(); i++)
            for (int j = 0; j < img.getWidth(); j++) {
                if (img.getRGB(i, j) != rotated.getRGB(i, j)) {
                    fail("Not equal images!");
                }
            }
    }
}

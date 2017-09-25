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
import java.awt.image.DataBufferByte;

import static org.junit.Assert.assertArrayEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageTests {

    @Test
    public void test01_Rotation() {
        BufferedImage img = ImageLoader.image("ui", "logo", true);
        BufferedImage rot90 = ImageUtilities.rotate(img, 90);
        BufferedImage rotated = ImageUtilities.rotate(rot90, 270);
        byte[] arrRot = ((DataBufferByte) rotated.getData().getDataBuffer()).getData();
        byte[] arrImg = ((DataBufferByte) img.getData().getDataBuffer()).getData();
        assertArrayEquals(arrRot, arrImg);

    }
}

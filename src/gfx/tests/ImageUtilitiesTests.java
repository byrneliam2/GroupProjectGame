package gfx.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;
import gfx.ImageUtilities;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageUtilitiesTests {

    @Ignore
    public void test01_Rotation() {
        BufferedImage img = ImageLoader.image("ui", "logo", true);
        BufferedImage rotated = ImageUtilities.rotate(ImageUtilities.rotate(img, 90), 270);

        DataBuffer dbActual = rotated.getRaster().getDataBuffer();
        DataBuffer dbExpected = img.getRaster().getDataBuffer();
        DataBufferByte dbiActual = (DataBufferByte) dbActual;
        DataBufferByte dbiExpected = (DataBufferByte) dbExpected;

        for (int bank = 0; bank < dbiActual.getNumBanks(); bank++) {
            byte[] actual = dbiActual.getData(bank);
            byte[] expected = dbiExpected.getData(bank);
            assertTrue(Arrays.equals(actual, expected));
        }
    }

    @Test
    public void test02_Scale() {
        BufferedImage img = ImageLoader.image("ui", "logo", true);
        BufferedImage scaled = ImageUtilities.scale(img, 200, 200);
        assertTrue(scaled.getWidth() == 200);
        assertTrue(scaled.getHeight() == 200);
    }
    //External Tests
    @Test
    public void test03_Scale() {
        BufferedImage img = ImageLoader.image("ui", "logo", true);
        int width = img.getWidth();
        int height =  img.getHeight();
        BufferedImage scaled = ImageUtilities.scale(img, 200, 200);
        assertTrue(scaled.getWidth() == 200);
        assertTrue(scaled.getHeight() == 200);
        assertTrue(scaled.getWidth()!=width);
        assertTrue(scaled.getHeight()!=height);
    }
}

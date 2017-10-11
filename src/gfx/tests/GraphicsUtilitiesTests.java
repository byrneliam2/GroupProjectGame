package gfx.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.GraphicsUtilities;
import gfx.ImageLoader;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.image.BufferedImage;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GraphicsUtilitiesTests {

    @Test
    public void test01_MakeButton() {
        BufferedImage main = ImageLoader.image("ui", "bu_new", true);
        BufferedImage roll = ImageLoader.image("ui", "bu_new_r", false);
        assertNotNull(GraphicsUtilities.produceButton(main, roll, 0));
    }

    @Test
    public void test02_MakeButton() {
        BufferedImage main = ImageLoader.image("ui", "bu_new", true);
        assertNotNull(GraphicsUtilities.produceButton(main, null, 0));
    }

    @Test
    public void test03_MakeButton() {
        BufferedImage main = null;
        BufferedImage roll = null;
        try {
            //no inspection ConstantConditions
            assertNotNull(GraphicsUtilities.produceButton(main, roll, 0));
            fail("Expecting IllegalArgumentException.");
        } catch (IllegalArgumentException ignored) {}
    }
    
    @Test
    public void test04_MakeSticker() {
        BufferedImage main = ImageLoader.image("ui", "logo", true);
        assertNotNull(GraphicsUtilities.produceSticker(main, 0));
    }

    @Test
    public void test05_MakeSticker() {
        BufferedImage main = null;
        try {
            //no inspection ConstantConditions
            assertNotNull(GraphicsUtilities.produceSticker(main, 0));
            fail("Expecting IllegalArgumentException.");
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void test06_MakeSlider() {
        assertNotNull(GraphicsUtilities.produceSlider());
    }
    
    //External Tester notes
    //I have ran the game and noted any visual bugs that i could see.
    //At this point the game runs well and without error, however there will
    //have to be some refactoring, as the front end should only be interacting with the back end
    //through interfaces.
}

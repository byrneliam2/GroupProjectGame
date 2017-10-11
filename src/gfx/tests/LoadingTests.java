package gfx.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import gfx.ImageLoader;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

import java.awt.Image;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadingTests {

	@Test
	public void test01_Loading() {
		assertNotEquals(null, ImageLoader.image("ui", "logo", true));
	}

	@Test
	public void test02_Loading() {
		try {
			ImageLoader.image("", "logo", true);
			fail("Expecting IllegalArgumentException.");
		} catch (IllegalArgumentException ignored) {
		}
	}

	// External Tests
	// Testing a successful loading
	@Test
	public void test03_Loading() {
		try {
			Image img = ImageLoader.image("ui", "logo", true);
			assertNotNull(img);
		} catch (IllegalArgumentException ignored) {
		}
	}
}

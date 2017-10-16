package map;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class contains all the methods required to translate scaled
 * images(layers) into 2D arrays of type integer.
 * 
 * @author James
 *
 */
public class LayerLoader {

	/**
	 * This method takes a buffered image representing the environment layer and
	 * converts it into a 2D array of integers. Each integer representing a type of
	 * environment
	 * 
	 * @param layer
	 * @return 2D ArrayList<Integer>
	 */
	public ArrayList<ArrayList<Integer>> translateImageToEnvironmentArray(BufferedImage layer) {
		int height = layer.getHeight() / Map.tileSize;
		int width = layer.getWidth() / Map.tileSize;
		ArrayList<ArrayList<Integer>> arrayLayer = this.retunBlankLayerArray(height, width);
		ArrayList<ArrayList<BufferedImage>> brokenLayer = this.splitImageIntoTiles(layer);

		for (int col = 0; col < height; col++) {
			for (int row = 0; row < width; row++) {
				Color c = new Color(brokenLayer.get(col).get(row).getRGB(1, 1));
				if (c.getGreen() == 0 && c.getRed() == 0 && c.getBlue() == 0) {// Black(1) is for death environment
					arrayLayer.get(col).add(1);
				} else if (c.getGreen() == 255 && c.getRed() == 0 && c.getBlue() == 0) {// Green(2) is for mud
					// environment
					arrayLayer.get(col).add(2);
				} else if (c.getGreen() == 0 && c.getRed() == 255 && c.getBlue() == 0) {// Red(3) is for fire
																						// environment
					arrayLayer.get(col).add(3);
				} else if (c.getGreen() == 0 && c.getRed() == 0 && c.getBlue() == 255) {// Blue(4) is for mist
																						// environment
					arrayLayer.get(col).add(4);
				} else {
					arrayLayer.get(col).add(0);// (0) is for no environment
				}

			}
		}
		return arrayLayer;
	}

	/**
	 * This method takes a buffered image representing a collision layer and
	 * converts it into a 2D array of integers. Each integer representing whether
	 * the buffered image was black or not.
	 * 
	 * @param layer
	 * @return 2D ArrayList<Integer>
	 */
	public ArrayList<ArrayList<Integer>> translateImageToCollisionArray(BufferedImage layer) {
		int height = layer.getHeight() / Map.tileSize;
		int width = layer.getWidth() / Map.tileSize;
		ArrayList<ArrayList<Integer>> arraylayer = this.retunBlankLayerArray(height, width);
		ArrayList<ArrayList<BufferedImage>> brokenlLayer = this.splitImageIntoTiles(layer);

		for (int col = 0; col < height; col++) {
			for (int row = 0; row < width; row++) {
				Color c = new Color(brokenlLayer.get(col).get(row).getRGB(1, 1));
				if (c.getGreen() == 0 && c.getRed() == 0 && c.getBlue() == 0) {
					arraylayer.get(col).add(1);

				} else {
					arraylayer.get(col).add(0);

				}
			}

		}
		return arraylayer;
	}

	/**
	 * This method breaks up a bufferedImage into a 2D array of sub images based off
	 * the TileSize.
	 * 
	 * @param layer
	 *            to split up
	 * @return A 2D array of BufferedImages split by the Map tileSize
	 */
	private ArrayList<ArrayList<BufferedImage>> splitImageIntoTiles(BufferedImage layer) {
		int widthUnbroken = layer.getWidth();
		int heightUnbroken = layer.getHeight();
		ArrayList<ArrayList<BufferedImage>> splitlayer = new ArrayList<ArrayList<BufferedImage>>();
		for (int initY = 0; initY < heightUnbroken; initY += Map.tileSize) {
			splitlayer.add(new ArrayList<BufferedImage>());
		}
		for (int y = 0; y < heightUnbroken; y += Map.tileSize) {
			for (int x = 0; x < widthUnbroken; x += Map.tileSize) {
				BufferedImage newTile = layer.getSubimage(x, y, Map.tileSize, Map.tileSize);
				int posY = y / Map.tileSize;
				splitlayer.get(posY).add(newTile);
			}
		}
		return splitlayer;
	}

	/**
	 * This method takes a tileHeight and tileWidth of a layer(BufferedImage) and
	 * creates an empty 2D array of integers. This method is used to prepare an
	 * array for image loading.
	 * 
	 * @param tileHeight
	 * @param tileWidth
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> retunBlankLayerArray(int tileHeight, int tileWidth) {

		ArrayList<ArrayList<Integer>> blankArrayLayer = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < tileHeight; i++) {
			blankArrayLayer.add(new ArrayList<Integer>(tileWidth));
		}
		return blankArrayLayer;
	}
}

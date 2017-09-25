package items;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gfx.ImageLoader;

public abstract class AbstractItem implements Item {
	protected final String name, description;
	protected Image image;
	protected Backpack pack;// is null if the item is not picked up
	protected final String imageFileName;

	/**
	 * @param itemName
	 * @param itemDescription
	 * @param imageFileName
	 *            the name of the image, NOT including the file extension, the file
	 *            should be located in itemList/ItemPictures folder (for now).
	 */
	public AbstractItem(String itemName, String itemDescription, String imageFileName) {
		this.name = itemName;
		this.description = itemDescription;
		this.imageFileName = imageFileName;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Backpack getPack() {
		return this.pack;
	}

	@Override
	public void pickUp(Backpack pack) {
		this.pack = pack;
	}

	@Override
	public void remove() {
		this.pack = null;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getImageFileName() {
		return imageFileName;
	}

}

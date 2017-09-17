package items;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class AbstractItem implements Item {
	protected String name, description;
	protected Image image;
	protected Backpack pack;// is null if the item is not picked up

	/**
	 * @param itemName
	 * @param itemDescription
	 * @param imageName
	 *            the name of the image, including the file extension, the file should be located in items/pictures
	 *            folder (for now).
	 */
	public AbstractItem(String itemName, String itemDescription, String imageName) {
		this.name = itemName;
		this.description = itemDescription;

		try {
			image = ImageIO.read((this.getClass().getResourceAsStream("pictures/" + imageName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}

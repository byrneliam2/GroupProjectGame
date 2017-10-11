package frames.cards;

import java.awt.Point;
import java.awt.image.BufferedImage;
import frames.cards.Card.EntityType;
import gfx.ImageLoader;

public class AnimationEntity extends Entity {
	
	private BufferedImage [] images = new BufferedImage[3];
	int currentImg = 0;

	public AnimationEntity(Object object, EntityType type, BufferedImage image, Point location, int size) {
		super(object, type, null, location, size);
		setupImages();
	}
	
	public void setupImages(){
		images[0] = ImageLoader.image("playerImages/playerAnimationImages", "B0", true);
		images[1] = ImageLoader.image("playerImages/playerAnimationImages", "B3", true);
		images[2] = ImageLoader.image("playerImages/playerAnimationImages", "B6", true);
	}

	@Override
	public BufferedImage getImage() {
		currentImg++;
		if(currentImg/10>=images.length)
			currentImg = 0;
		return images[currentImg/10];
	}
}

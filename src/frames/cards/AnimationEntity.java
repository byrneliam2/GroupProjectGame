package frames.cards;

import java.awt.Point;
import java.awt.image.BufferedImage;

import common.player.IPlayer;
import common.utils.Direction;
import frames.cards.Card.EntityType;
import gfx.ImageLoader;
import gfx.ImageUtilities;

public class AnimationEntity extends Entity {

	private BufferedImage[] backImages = new BufferedImage[9];
	private BufferedImage[] frontImages = new BufferedImage[9];
	private BufferedImage[] leftImages = new BufferedImage[9];
	private BufferedImage[] rightImages = new BufferedImage[9];
	int currentImg = 0;
	final int framesPerImage = 20;
	IPlayer player;

	public AnimationEntity(IPlayer player, EntityType type, BufferedImage image, Point location, int size) {
		super(player, type, null, location, size);
		this.player = player;
		setupImages();
	}

	public void setupImages() {
		for (int i = 0; i < backImages.length; i++) {
			backImages[i] = ImageLoader.image("playerImages/playerAnimationImages", "B" + i, true);
		}
		for (int i = 0; i < frontImages.length; i++) {
			frontImages[i] = ImageLoader.image("playerImages/playerAnimationImages", "F" + i, true);
		}
		for (int i = 0; i < leftImages.length; i++) {
			leftImages[i] = ImageLoader.image("playerImages/playerAnimationImages", "L" + i, true);
		}
		for (int i = 0; i < rightImages.length; i++) {
			//got to flip this image cause lazy james XD
			rightImages[i] = ImageUtilities.cropImage(ImageUtilities
					.flipHorizontal(ImageLoader.image("playerImages/playerAnimationImages", "R" + i, true)), new Rectangle());
		}
	}

	@Override
	public BufferedImage getImage() {
		currentImg++;
		if (currentImg / framesPerImage >= backImages.length)
			currentImg = 1;

		if (player.isMoving()) {

			if (player.getCurrentDir() == Direction.N)
				return backImages[currentImg / framesPerImage];
			else if (player.getCurrentDir() == Direction.S)
				return frontImages[currentImg / framesPerImage];
			else if (player.getCurrentDir() == Direction.W)
				return leftImages[currentImg / framesPerImage];
			else
				return rightImages[currentImg / framesPerImage];
		}else{//player isn't moving
			if (player.getCurrentDir() == Direction.N)
				return backImages[0];
			else if (player.getCurrentDir() == Direction.S)
				return frontImages[0];
			else if (player.getCurrentDir() == Direction.W)
				return leftImages[0];
			else
				return rightImages[0];
		}
	}
}

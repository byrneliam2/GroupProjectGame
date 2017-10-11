package frames.cards;

import java.awt.Point;
import java.awt.image.BufferedImage;

import common.player.IPlayer;
import common.utils.Direction;
import frames.cards.Card.EntityType;
import gfx.ImageLoader;
import gfx.ImageUtilities;

/**
 * Class responsible for loading the animations of the player as he moves in
 * different directions.
 *
 * @author Thomas Edwards
 */
public class AnimationEntity extends Entity {

	//all the animation images for moving in different directions.
	private BufferedImage[] backImages = new BufferedImage[9];
	private BufferedImage[] frontImages = new BufferedImage[9];
	private BufferedImage[] leftImages = new BufferedImage[9];
	private BufferedImage[] rightImages = new BufferedImage[9];
	
	private int currentImg = 0;
	private final int framesPerImage = 20;// how many frames per image of the animation.
	private final int scale = 60;// the scale of the image.
	private IPlayer player;

	public AnimationEntity(IPlayer player, EntityType type, BufferedImage image, Point location, int size) {
		super(player, type, null, location, size);
		this.player = player;
		setupImages();
	}

	/**
	 * Loads all the images part of this animation into the buffered array.
	 */
	private void setupImages() {
		for (int i = 0; i < backImages.length; i++) {
			BufferedImage img = ImageLoader.image("playerImages/playerAnimationImages", "B" + i, true);
			img = ImageUtilities.scale(img, scale, scale);
			backImages[i] = img;
		}
		for (int i = 0; i < frontImages.length; i++) {
			BufferedImage img = ImageLoader.image("playerImages/playerAnimationImages", "F" + i, true);
			img = ImageUtilities.scale(img, scale, scale);
			frontImages[i] = img;
		}
		for (int i = 0; i < leftImages.length; i++) {
			BufferedImage img = ImageLoader.image("playerImages/playerAnimationImages", "L" + i, true);
			img = ImageUtilities.scale(img, scale, scale);
			leftImages[i] = img;
		}
		for (int i = 0; i < rightImages.length; i++) {
			// got to flip this image cause lazy james XD
			BufferedImage img = ImageLoader.image("playerImages/playerAnimationImages", "R" + i, true);
			img = ImageUtilities.flipHorizontal(img);
			img = ImageUtilities.scale(img, scale, scale);
			rightImages[i] = img;
		}
	}

	@Override
	public BufferedImage getImage() {
		currentImg++;
		if (currentImg / framesPerImage >= backImages.length)
			currentImg = 1;

		if (player.isMoving()) {
			// moving animation images...
			if (player.getCurrentDir() == Direction.N)
				return backImages[currentImg / framesPerImage];
			else if (player.getCurrentDir() == Direction.S)
				return frontImages[currentImg / framesPerImage];
			else if (player.getCurrentDir() == Direction.W)
				return leftImages[currentImg / framesPerImage];
			else
				return rightImages[currentImg / framesPerImage];
		} else {// player isn't moving
			// standing still images...
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

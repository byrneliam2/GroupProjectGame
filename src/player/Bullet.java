package player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import common.player.IBullet;
import common.utils.DisplayValues;
import game.Game;
import gfx.ImageLoader;
import gfx.ImageUtilities;
import map.Environment;

/**
 * A bullet is a point object with an x,y location, when the bullet is created a
 * timer is started which updates the bullet location until the bullet hits a
 * wall or npc.
 *
 * @author Thomas Edwards
 *
 */
public class Bullet implements IBullet {

	/**
	 * The list of all current bullets in the game.
	 */
	//public static List<Bullet> bulletList = new ArrayList<>();
	/**
	 * Timer which is responsible for updating all bullets in the bullet list.
	 */
	private static Timer bulletTimer = new Timer();

	//public static final int bulletSize = 20;

	/**
	 * How quickley bullets move/are updated
	 */
	private final int bulletSpeed;

	private static BufferedImage playerBullet1 = ImageLoader.image("playerImages", "bullet", true);
	private static BufferedImage npcBullet1 = ImageLoader.image("npcImages", "bullet", true);
	private static BufferedImage npcBullet2 = ImageLoader.image("npcImages", "bullet2", true);
	private static BufferedImage npcBullet3 = ImageLoader.image("npcImages", "bullet3", true);
	private static BufferedImage npcBullet4 = ImageLoader.image("npcImages", "bullet4", true);
	{
		{
			playerBullet1 = ImageUtilities.scale(playerBullet1, bulletSize, bulletSize);
			npcBullet1 = ImageUtilities.scale(npcBullet1, bulletSize, bulletSize);
			npcBullet2 = ImageUtilities.scale(npcBullet2, bulletSize, bulletSize);
			npcBullet3 = ImageUtilities.scale(npcBullet3, bulletSize, bulletSize);
			npcBullet4 = ImageUtilities.scale(npcBullet4, bulletSize, bulletSize);
		}
	}

	private double currentX, currentY;
	private double updateX, updateY, halfX, halfY;
	private Player owner;
	private TimerTask bulletTask;
	private String bulletType;

	/**
	 * Creates a new bullet and timer which updates the bullets location. The timer
	 * is started as soon as the bullet is created.The timer updates the bullet's
	 * location. {@link #removeBullet()} should always be called when removing a
	 * bullet as otherwise the timer just keeps going. Adds the bullet to the bullet
	 * list.
	 *
	 * @param startingX
	 *            ussually the player's current location
	 * @param startingY
	 *            ussually the player's current location
	 * @param direction
	 *            an angle in radians between 0 (straight up) and 2PI (also straight
	 *            up). Pi/2 would be right, Pi would be down, 3Pi/2 would be left.
	 * @param owner
	 *            the owner of the bullet
	 */
	public Bullet(double startingX, double startingY, double direction, Player owner, int bulletSpeed,
			String bulletType) {
		currentX = startingX;
		currentY = startingY;
		this.bulletSpeed = bulletSpeed;
		this.bulletType = bulletType;
		this.owner = owner;
		calculateUpdateAmount(direction);
		halfX = updateX / 2;
		halfY = updateY / 2;
		bulletList.add(this);

		startTimer();// starts the bullet off
	}

	/**
	 * Stops the bullet from moving any further and deletes it from the bullet list.
	 */
	public void removeBullet() {
		bulletTask.cancel();
		bulletList.remove(this);
	}

	/**
	 * @return the current x location of this bullet
	 */
	public double getX() {
		return currentX;
	}

	public BufferedImage getBulletPic() {
		if (bulletType.equals("playerBullet1"))
			return Bullet.playerBullet1;
		if (bulletType.equals("npcBullet1"))
			return Bullet.npcBullet1;
		if (bulletType.equals("npcBullet2"))
			return Bullet.npcBullet2;
		if (bulletType.equals("npcBullet3"))
			return Bullet.npcBullet3;
		if (bulletType.equals("npcBullet4"))
			return Bullet.npcBullet4;

		throw new Error("The Bullet type: " + bulletType + " is not implemented yet");
	}

	/**
	 * @return the current y location of this bullet.
	 */
	public double getY() {
		return currentY;
	}

	/**
	 * @return the player/NPC who owns this bullet.
	 */
	public Player getOwner() {
		return this.owner;
	}

	private void startTimer() {
		bulletTask = new TimerTask() {
			@Override
			public void run() {
				update();
			}
		};
		bulletTimer.scheduleAtFixedRate(bulletTask, 0, DisplayValues.FRAMERATE);
	}

	/**
	 * Updates the bullet location, stops the bullet if it runs into an immovable
	 * location of the map or another player/npc.
	 */
	private void update() {
		if (Game.GAME_PAUSED) {// do no updates when paused...
			return;
		}
		if (owner.getMap().onEnvironmentTile((int) currentX, (int) currentY) == Environment.MIST) {
			currentX += halfX;
			currentY += halfY;
		} else {
			currentX += updateX;
			currentY += updateY;
		}

		// if the bullet hits an immovable area, remove it.
		if (!owner.getMap().canMove((int) currentX, (int) currentY)) {
			removeBullet();
		}
		// if the bullet hits a npc or player, remove it
		if (owner.getMap().checkBulletHit(this)) {
			removeBullet();
		}
		// removes the bullet if it somehow gets off the map
		if (currentX > 2000 || currentX < 0 || currentY > 2000 || currentY < 0) {
			removeBullet();
		}
	}

	private void calculateUpdateAmount(double angle) {
		if (angle < Math.PI / 2) {
			updateY = -Math.cos(angle) * bulletSpeed;
			updateX = Math.sin(angle) * bulletSpeed;
		} else if (angle < Math.PI) {
			angle = angle - Math.PI / 2;
			updateY = Math.sin(angle) * bulletSpeed;
			updateX = Math.cos(angle) * bulletSpeed;
		} else if (angle < 3 * Math.PI / 2) {
			angle = angle - Math.PI;
			updateY = Math.cos(angle) * bulletSpeed;
			updateX = -Math.sin(angle) * bulletSpeed;
		} else if (angle < 2 * Math.PI) {
			angle = angle - 3 * Math.PI / 2;
			updateY = -Math.sin(angle) * bulletSpeed;
			updateX = -Math.cos(angle) * bulletSpeed;
		} else {
			throw new Error("Angle given was greater than 2Pi");
		}

	}
}

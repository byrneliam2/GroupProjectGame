package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A bullet is a point object with an x,y location, when the bullet is created a
 * timer is started which updates the bullet location until the bullet hits a
 * wall or npc.
 *
 * @author Thomas Edwards
 *
 */
public class Bullet {

	/**
	 * The list of all current bullets in the game.
	 */
	public static List<Bullet> bulletList = new ArrayList<>();
	/**
	 * How quickley bullets move/are updated
	 */
	private static final int rateOfUpdate = 100;
	/**
	 * Timer which is responsible for updating all bullets in the bullet list.
	 */
	private static Timer bulletTimer = new Timer();

	private double currentX, currentY;
	private double updateX, updateY;
	private Player owner;
	private TimerTask bulletTask;

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
	public Bullet(double startingX, double startingY, double direction, Player owner) {
		currentX = startingX;
		currentY = startingY;
		this.owner = owner;
		calculateUpdateAmount(direction);
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
		bulletTimer.scheduleAtFixedRate(bulletTask, 0, rateOfUpdate);
	}

	/**
	 * Updates the bullet location, stops the bullet if it runs into an immovable
	 * location of the map or another player/npc.
	 */
	private void update() {
		currentX += updateX;
		currentY += updateY;
		// if the bullet hits an immovable area, remove it.
		if (owner.getMap() == null)
			System.out.println("ghdfg");
		if (!owner.getMap().canMove((int) currentX, (int) currentY)) {
			removeBullet();
		}
		// if the bullet hits a npc, remove it
		if (owner.getMap().checkBulletHit(this)) {
			removeBullet();
		}
	}

	private void calculateUpdateAmount(double angle) {
		if (angle < Math.PI / 2) {
			updateY = Math.cos(angle);
			updateX = Math.sin(angle);
		} else if (angle < Math.PI) {
			angle = angle - Math.PI / 2;
			updateY = -Math.sin(angle);
			updateX = Math.cos(angle);
		} else if (angle < Math.PI) {
			angle = angle - Math.PI;
			updateY = -Math.cos(angle);
			updateX = -Math.sin(angle);
		} else if (angle < 2 * Math.PI) {
			angle = angle - 3 * Math.PI / 2;
			updateY = Math.sin(angle);
			updateX = -Math.cos(angle);
		} else {
			throw new Error("Angle given was greater than 2Pi");
		}

	}
}

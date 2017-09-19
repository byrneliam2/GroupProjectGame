package player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * A bullet is a point object with an x,y location, when the bullet is created a
 * timer is started which updates the bullet location until the bullet hits
 * either a player, or a wall.
 *
 * @author Thomas Edwards
 *
 */
public class Bullet {

	public static List<Bullet> bulletList = new ArrayList<>();
	private static final int rateOfUpdate = 100;

	private double currentX, currentY;
	private double updateX, updateY;
	private Player owner;
	private Timer timer;

	/**
	 * Creates a new bullet and timer which updates the bullets location. The timer
	 * is started as soon as the bullet is created.The timer updates the bullet's
	 * location. stop() should always be called when removing a bullet as otherwise
	 * the timer just keeps going. Adds the bullet to the bullet list.
	 *
	 * @param startingX
	 * @param startingY
	 * @param direction
	 *            an angle between 0 (straight up) and 2PI (also straight up). Pi/2
	 *            would be right, Pi would be down, 3Pi/2 would be left.
	 */
	public Bullet(double startingX, double startingY, double direction, Player owner) {
		currentX = startingX;
		currentY = startingY;
		this.owner = owner;
		calculateUpdateAmount(direction);
		bulletList.add(this);
		startTimer();
	}

	/**
	 * Stops the bullet from moving any further.
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * @return the x location of this bullet
	 */
	public double getX() {
		return currentX;
	}

	/**
	 * @return the y location of this bullet.
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
		timer = new Timer(rateOfUpdate, (e) -> update());
		timer.start();
	}

	/**
	 * Updates the bullet location, stops the bullet if runs into an immovable
	 * location of the map.
	 */
	private void update() {
		currentX += updateX;
		currentY += updateY;
		if (!owner.getMap().canMove((int) currentX, (int) currentY)) {// if the bullet hits an immovable area, stop it.
			timer.stop();
			bulletList.remove(this);
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

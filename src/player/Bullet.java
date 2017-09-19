package player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class Bullet {

	public static List<Bullet> bulletList = new ArrayList<>();

	private static final int rateOfUpdate = 100;
	private static final int maxbulletDistance = 100;

	private double currentX, currentY;
	private double updateX, updateY;
	private Player owner;
	private Timer timer;

	/**
	 * Creates a new bullet and timer which updates the bullets location. The timer
	 * is started as soon as the bullet is created.The tmimer updates the bullet's
	 * location. stop() should always be called when removing a bullet as otherwise
	 * the timer just keeps going.
	 *
	 * @param startingX
	 * @param startingY
	 * @param direction
	 *            an angle between 0 (straight up) and 2PI (also straightup). Pi/2
	 *            would be right, Pi would be down, 3Pi/2 would be left
	 */
	public Bullet(double startingX, double startingY, double direction, Player owner) {
		currentX = startingX;
		currentY = startingY;
		this.owner = owner;
		calculateUpdateAmount(direction);
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

	public Player getOwner() {
		return this.owner;
	}

	private void startTimer() {
		timer = new Timer(rateOfUpdate, (e) -> update());
		timer.start();
	}

	/**
	 * Updates the bullet location, stops the bullet if
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
			throw new Error("Angle given was not between 0 and 2PI");
		}

	}
}

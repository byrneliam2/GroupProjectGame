package common.utils;

/**
 * Shared Utility class for various different mathematical calculations
 *
 * @author Thomas Edwards
 */
public class MathUtils {

	/**
	 * Calculate the angle from p1, to p2. Returns an angle between 0 and 2Pi. 0 is
	 * up, Pi is down, 2Pi is up etc.
	 * 
	 * @param x1
	 *            Entity X coordinate
	 * @param y1
	 *            Entity Y coordinate
	 * @param x2
	 *            Player's X coordinate
	 * @param y2
	 *            Player's Y coordinate
	 * @return the angle from p1 to p2 between 0 and 2Pi. 0 would mean p2 is
	 *         directly above p1.
	 */
	public static double calculateAngle(double x1, double y1, double x2, double y2) {
		double x = x1 - x2;
		double y = y1 - y2;

		// math to calculate 360 degree angle.
		double angle;
		if (x > 0 && y >= 0) {// top left corner
			angle = 3 * Math.PI / 2 + Math.atan(y / x);
		} else if (x >= 0 && y < 0) {// bottom left corner
			angle = Math.PI - Math.atan(x / y);
		} else if (x < 0 && y <= 0) {// bottom right corner
			angle = Math.PI / 2 + Math.atan(y / x);
		} else {// else if (x <= 0 && y > 0)//top right corner
			angle = -Math.atan(x / y);
		}

		return angle;
	}

	/**
	 * Gets the absolute distance from point 1 to point 2.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.hypot(x1 - x2, y1 - y2);
	}
}

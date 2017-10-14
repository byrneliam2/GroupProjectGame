package common.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Thomas and Liam
 */
public enum Direction {

	N(0, -1), E(1, 0), S(0, 1), W(-1, 0),

	NE(0.785, -0.785), SE(0.785, 0.785), SW(-0.785, 0.785), NW(-0.785, -0.785);

	/**
	 * List of all values in the enum. I use it to get a random direction in the ai.
	 * Used because creating an array each time for a random direction is
	 * inefficient.
	 */
	private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private double x, y;

	private Direction(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public static Direction getRandomDirection() {
		int random = (int) (Math.random() * 8);
		return VALUES.get(random);
	}

}

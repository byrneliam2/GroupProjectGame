package utils;

/**
 * Shared Utility class for various different calculations
 */
public class MathUtils {

    /**
     * Calculate the angle between two points
     * @param x1 Entity X coordinate
     * @param y1 Entity Y coordinate
     * @param x2 Player's X coordinate
     * @param y2 Player's Y coordinate
     * @return the angle of each point
     */
    public static double calculateAngle(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;

        // math to calculate 360 degree angle.
        // might have to catch divide-by-0 errors. TEST those!
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
}

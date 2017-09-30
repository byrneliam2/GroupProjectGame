package utils;

public enum Direction {
    N(0,-1),
    E(1, 0),
    S(0, 1),
    W(-1, 0),
    
	NE(0.785,-0.785),
	SE(0.785,0.785),
	SW(-0.785,0.785),
	NW(-0.785,-0.785);
	

    private double x,y;

    Direction(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

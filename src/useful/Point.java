package useful;

import wormgame.domain.Locatable;

import java.util.Objects;

public class Point {
    private static Randomizer randomizer;
    private int x;
    private int y;

    public Point(int x, int y) {
        setX(x);
        setY(y);
        randomizer = new Randomizer();
    }

    public Point() {
        this(0, 0);
    }

    public Point(Point other) {
        this(other.x, other.y);
    }

    public static Point randomPoint(int xMin, int xMax, int yMin, int yMax) {
        return new Point(randomizer.random(xMin, xMax),
                randomizer.random(yMin, yMax));
    }

    public static Point randomPoint(int xMax, int yMax) {
        return randomPoint(0, xMax - 1, 0, yMax - 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setX(int dx) {
        x = dx;
    }

    private void setY(int dy) {
        y = dy;
    }

    public Point getRelative(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        Point other = (Point) object;
        return x == other.x
                && y == other.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}

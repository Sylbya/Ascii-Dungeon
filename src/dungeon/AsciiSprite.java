package dungeon;

import useful.Direction;
import useful.Point;

import java.util.Objects;

public class AsciiSprite {
    private Point point;
    private Point previousPoint;
    private String symbol;

    public AsciiSprite(String symbol) {
        this.symbol = symbol;
        this.point = new Point();
        this.previousPoint = new Point();
    }

    public String getSymbol() {
        return symbol;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point newPoint) {
        point = newPoint;
    }

    public boolean hasMoved() {
        return !point.equals(previousPoint);
    }

    public boolean isAtThisPosition(Point other) {
        return point.equals(other);
    }

    public void rewindMove() {
        point = previousPoint;
    }

    public void move(Direction direction, Dungeon dungeon) {
        previousPoint = new Point(point);

        //setPoint(direction.move.apply(point));

        if (!dungeon.isInBoundary(point)) {
            rewindMove();
        }
        System.out.println("------------") ;
    }

    public String coordinates() {
        return String.format("%s %d %d", symbol, point.getX(), point.getY());
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
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
        AsciiSprite other = (AsciiSprite) object;

        return Objects.equals(symbol, other.symbol)
                && point.equals(other.point);
    }
}
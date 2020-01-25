package dungeon;


import useful.Direction;
import useful.Point;

import java.util.*;

public class VampireGroup {
    private List<AsciiSprite> vampires;
    private boolean doesMove;

    public VampireGroup(int count, boolean doesMove) {
        this.vampires = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            this.vampires.add(new AsciiSprite("v"));
        }
        this.doesMove = doesMove;
    }

    public boolean areAshes() {
        return vampires.isEmpty();
    }

    public String getSymbol() {
        return vampires.get(0).getSymbol();
    }

    public Set<Point> getPoints() {
        Set<Point> points = new HashSet<>();
        for (AsciiSprite vampire : this.vampires) {
            points.add(vampire.getPoint());
        }
        return points;
    }

    public AsciiSprite vampireAtPosition(Point point) {
        for (AsciiSprite vampire : vampires) {
            if (vampire.isAtThisPosition(point)) {
                return vampire;
            }
        }
        return null;
    }

    public void removeAshes(Point playerPoint) {
        // do nothing if no vampires are found
        vampires.remove(vampireAtPosition(playerPoint));
    }

    public void setInitialCoordinates(int length, int height,
                                      Set<Point> usedPoints) {
        for (AsciiSprite vampire : vampires) {
            usedPoints.remove(vampire.getPoint());
            setToRandomCoordinate(length, height, vampire, getPoints());
            usedPoints.add(vampire.getPoint());
        }
    }

    private void setToRandomCoordinate(int length,
                                      int height,
                                      AsciiSprite vampire,
                                      Set<Point> usedPoints) {
        Point newPoint;
        do {
            newPoint = Point.randomPoint(length, height);
        }
        while (usedPoints.contains(newPoint));
        vampire.setPoint(newPoint);
    }

    private void moveRandomly(Dungeon dungeon, AsciiSprite vampire,
                             Set<Point> usedPoints) {
        Direction randomDirection = Direction.getRandomDirection();
        vampire.move(randomDirection, dungeon);
        if (usedPoints.contains(vampire.getPoint())) {
            vampire.rewindMove();
            moveRandomly(dungeon, vampire, usedPoints);
        }
    }

    public void move(Dungeon dungeon) {
        if (!doesMove) {
            return;
        }
        for (AsciiSprite vampire : vampires) {
            do {
                moveRandomly(dungeon, vampire, dungeon.usedPoints());
            }
            while (!vampire.hasMoved());
        }
    }

    public String coordinates() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AsciiSprite vampire : this.vampires) {
            stringBuilder.append(String.format("%s\n", vampire.coordinates()));
        }
        return stringBuilder.toString();
    }
}


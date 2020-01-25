package dungeon;


import useful.Direction;
import useful.Point;

import java.util.HashSet;
import java.util.Set;

public class Dungeon {
    private int length;
    private int height;
    private int lampCount;
    private AsciiSprite player;
    private VampireGroup vampires;

    public Dungeon(int length, int height, int lampCount) {
        this.length = length;
        this.height = height;
        this.lampCount = lampCount;
        this.player = new AsciiSprite("@");
        this.vampires = new VampireGroup(2, true);
        this.vampires.setInitialCoordinates(length, height, usedPoints());
    }

    public void printResults() {
        if (vampires.areAshes()) {
            System.out.println("YOU WIN");
        }
        else {
            System.out.println("YOU LOSE");
        }
    }

    public boolean isNotClearedWhenPlayerIsAlive() {
        return lampCount > 0 && !vampires.areAshes();
    }


    public Set<Point> usedPoints() {
        Set<Point> usedPoints = new HashSet<>();
        usedPoints.add(player.getPoint());
        usedPoints.addAll(vampires.getPoints());
        return usedPoints;
    }

    public void printStats() {
        System.out.println(lampCount);
        System.out.println();

        printCoordinates();
        System.out.println();

        printGrid(player, vampires, usedPoints());
        System.out.println();
    }

    public void playersTurn(Direction direction) throws IllegalArgumentException {
        player.move(direction, this);
    }

    public void vampiresTurn() {
        vampires.removeAshes(player.getPoint());
        vampires.move(this);
    }

    public void printCoordinates() {
        System.out.println(player.coordinates());
        System.out.println(vampires.coordinates());
    }

    public boolean isInBoundary(Point point) {
        return  isInRange(point.getX(), length)
                && isInRange(point.getY(), height);
    }

    public void decrementLampCount() {
        if (player.hasMoved()) {
            lampCount--;
        }
    }

    public boolean isInRange(int value, int min, int max) {
        return value >= min && value < max;
    }

    public boolean isInRange(int value, int max) {
        return isInRange(value, 0, max);
    }

    public void printGrid(AsciiSprite player, VampireGroup vampires, Set<Point> usedPoints) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                Point currentPoint = new Point(j, i);
                if (usedPoints.contains(currentPoint)) {
                    if (player.isAtThisPosition(currentPoint)) {
                        System.out.print(player);
                    }
                    else {
                        System.out.print(vampires.getSymbol());
                    }
                }
                else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
}

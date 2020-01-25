package useful;

import java.util.function.Function;


public enum Direction {

    UP((point -> point.getRelative(0, - 1))),
    LEFT((point -> point.getRelative(-1, 0))),
    DOWN((point -> point.getRelative(0, 1))),
    RIGHT((point -> point.getRelative(1, 0)))
    ;

    public final Function<Point, Point> move;
    public static Randomizer randomizer;

    static {
        randomizer = new Randomizer();
    }

    Direction(Function<Point, Point> move) {
        this.move = move;
    }

    public static Direction getRandomDirection() {
        int noOfKeys = Direction.values().length;
        int randomNumber = randomizer.random(noOfKeys);
        return Direction.values()[randomNumber];
    }
}


//    UP((point -> {point.setRelativeY(-1); return point;})),
//    LEFT((point -> {point.setRelativeX(-1); return point;})),
//    DOWN((point -> {point.setRelativeY(1); return point;})),
//    RIGHT((point -> {point.setRelativeX(1); return point;}));

//    UP((point -> new Point(2, 2))),
//    LEFT(point -> new Point(2, 2)),
//    DOWN((point -> new Point(2, 2))),
//    RIGHT((point -> new Point(2, 2)))
//    ;

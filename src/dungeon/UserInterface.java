package dungeon;

import useful.Direction;
import useful.Randomizer;

import java.util.*;

public class UserInterface implements AutoCloseable {
    private static final UserInterface userInterface = new UserInterface();
    private Scanner scanner;
    private Dungeon dungeon;
    private Map<String, Direction> keys;
    private static Randomizer randomizer = new Randomizer();

    private UserInterface() {
        this.scanner = new Scanner(System.in);
        this.dungeon = new Dungeon(5, 5, 14);

        this.keys = new HashMap<>();
        String[] asciiKeys = {"w", "a", "s", "d"};
        for (int i = 0; i < asciiKeys.length; i++) {
            this.keys.put(asciiKeys[i], Direction.values()[i]);
        }
    }

    public List<Direction> moves(String multipleCommands) throws IllegalArgumentException {
        List<Direction> directions = new ArrayList<>();
        for (char command : multipleCommands.toCharArray()) {
            Direction direction = keys.get(String.valueOf(command));
            if (direction == null) {
                throw new IllegalArgumentException("Illegal key: " + command);
            }
            directions.add(direction);
        }
        return directions;
    }

    public static UserInterface getInstance() {
        return userInterface;
    }

    public void executeCommands(String multipleCommands)
            throws IllegalArgumentException {
        for (Direction direction : moves(multipleCommands)) {
            dungeon.playersTurn(direction);
        }
        dungeon.vampiresTurn();
        dungeon.decrementLampCount();
    }

    public void run() {
        while (dungeon.isNotClearedWhenPlayerIsAlive()) {
            dungeon.printStats();
            if (scanner.hasNext()) {
                String commandSequence = scanner.next();
                try {
                    executeCommands(commandSequence);
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
            System.out.println("--------------------------");
        }
        dungeon.printResults();
    }

    public static void main(String[] args) {
        try (UserInterface ui = UserInterface.getInstance()) {
            ui.run();
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}

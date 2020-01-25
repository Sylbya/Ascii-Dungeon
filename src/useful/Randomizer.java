package useful;

import java.util.Random;

public class Randomizer extends Random {
    //inclusive
    public int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public double randomDouble(double min, int max) {
        Random random = new Random();
        return random.nextDouble()*(max - min) + min;
    }

    //exclusive
    public int random(int n) {
        return random(0, n - 1);
    }
}
package nl.itvitae.foo;

import nl.itvitae.foo.game.Game;

import java.util.Random;

public class Main {

    private static final long SEED = 836029326159674153L;

    public static void main(String[] args) {
        long seed;

        if (args.length > 0) {
            seed = Long.valueOf(args[0]);
        } else {
            seed = new Random().nextLong();
        }

        new Game(seed, 60).start();
    }
}

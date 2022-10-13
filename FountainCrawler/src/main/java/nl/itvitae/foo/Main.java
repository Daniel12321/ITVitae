package nl.itvitae.foo;

import nl.itvitae.foo.game.Game;

import java.util.Random;

public class Main {

//    private static final long SEED = 836029326159674153L;

    public static void main(String[] args) {
        long seed = args.length > 0 ? Long.parseLong(args[0]) : new Random().nextLong();

        new Game(seed, 60).start();
    }
}

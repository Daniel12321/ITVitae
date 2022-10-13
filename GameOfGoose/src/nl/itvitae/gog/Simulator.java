package nl.itvitae.gog;

import nl.itvitae.gog.game.Color;
import nl.itvitae.gog.game.Game;
import nl.itvitae.gog.game.Goose;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.LongStream;

public class Simulator {

    private static final Color[] COLORS = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    private static final long[] HEATMAP = new long[64];
    private static int redWins = 0, greenWins = 0, blueWins = 0, yellowWins = 0, moves = 0;

    private static final int BATCH_SIZE = 10_000;
    private static final Object LOCK = new Object();
    private static CountDownLatch latch;

    public static void main(String[] args) {
        Game.REQUIRE_ENTER = false;
        Game.DO_PRINTS = false;
        Game.DO_SLEEPS = false;

        Scanner s = new Scanner(System.in);
        System.out.print("Amount of games: ");
        int games = Integer.parseInt(s.nextLine());
        System.out.println();

        System.out.print("Amount of players: ");
        int players = Integer.parseInt(s.nextLine());
        System.out.println();

//        Simulator.simulateSync(games, players);
        Simulator.simulateAsync(games, players);
    }

    public static void simulateSync(int games, int players) {
        long beginTime = System.currentTimeMillis();

        simulate(games, players);

        long endTime = System.currentTimeMillis();

        printInfo(endTime - beginTime, moves, games, redWins, greenWins, blueWins, yellowWins);
        printHeatmap(HEATMAP, games);
    }

    public static void simulateAsync(final int games, final int players) {
        final int batches = games / BATCH_SIZE;

        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Math.min(Math.max(1, batches), 12));
        latch = new CountDownLatch(batches + (games % BATCH_SIZE > 1 ? 1 : 0));

        final long beginTime = System.currentTimeMillis();

        for (int i = 0; i < batches; i++)
            pool.submit(() -> simulate(BATCH_SIZE, players));

        if (games % BATCH_SIZE > 0)
            pool.submit(() -> simulate(games % BATCH_SIZE, players));

//        pool.submit(() -> simulate((games % BATCH_SIZE > 0 ? games % BATCH_SIZE : BATCH_SIZE), players));

        try {
            latch.await();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        pool.shutdown();

        final long endTime = System.currentTimeMillis();

        printInfo(endTime - beginTime, moves, games, redWins, greenWins, blueWins, yellowWins);
        printHeatmap(HEATMAP, games);
    }

    private static void simulate(final int games, final int players) {
        final long[] heatmap = new long[64];
        int moves = 0, redWins = 0, greenWins = 0, blueWins = 0, yellowWins = 0;

        for (int i = 0; i < games; i++) {
            Goose[] geese = getGeese(players);
            Game game = new Game(geese);
            game.start();
            moves += game.getMoves();

            if (geese[0].isFinished()) {
                redWins++;
            } else if (geese.length >= 2 && geese[1].isFinished()) {
                greenWins++;
            } else if (geese.length >= 2 && geese[2].isFinished()) {
                blueWins++;
            } else if (geese.length >= 3 && geese[3].isFinished()) {
                yellowWins++;
            }

            for (int h = 0; h < 64; h++)
                heatmap[h] += game.getHeatMap()[h];
        }

        addStats(heatmap, moves, redWins, greenWins, blueWins, yellowWins);
        if (latch != null)
            latch.countDown();
    }

    private static Goose[] getGeese(int players) {
        Goose[] geese = new Goose[players];

        for (int i = 0; i < players; i++)
            geese[i] = new Goose(COLORS[i]);

        return geese;
    }

    private static void printInfo(long time, int moves, int games, int red, int green, int blue, int yellow) {
        System.out.println();
        System.out.println("Simulation took " + time + " millis!");
        System.out.println();
        System.out.println("   Red won " + red + " games!");
        System.out.println(" Green won " + green + " games!");
        System.out.println("  Blue won " + blue + " games!");
        System.out.println("Yellow won " + yellow + " games!");
        System.out.println();
        System.out.println("A total of " + moves + " moves were made, averaging " + moves / games + " moves per game.");
        System.out.println();
    }

    private static void printHeatmap(long[] heatmap, int games) {
        double scale = 20.0;

        int row = (int) (LongStream.of(heatmap).reduce(Math::max).getAsLong() * scale / games);

        for (; row > 0; row--) {
            for (int i = 0; i < 64; i++)
                System.out.print(row > heatmap[i] * scale / games ? "   " : " * ");

            System.out.println();
        }

        System.out.println(" 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63");


//        for (int i = 0; i < 64; i++)
//            System.out.println("Tile " + i + ": " + heatmap[i] + "  (" + (Math.round(heatmap[i] / (double)games * 100) / 100.0) + " per game)");
    }

    private static void addStats(long[] heatmap, int moves, int red, int green, int blue, int yellow) {
        synchronized (LOCK) {
            for (int i = 0; i < 64; i++)
                Simulator.HEATMAP[i] += heatmap[i];

            Simulator.moves += moves;
            redWins += red;
            greenWins += green;
            blueWins += blue;
            yellowWins += yellow;
        }
    }
}

package nl.itvitae.gog.game;

import nl.itvitae.gog.dice.Roll;

import java.util.Scanner;

public class Game {

    public static boolean REQUIRE_ENTER = false;
    public static boolean DO_PRINTS = true;
    public static boolean DO_SLEEPS = true;

    private final Scanner scanner = new Scanner(System.in);
    private final long[] heatmap = new long[64];

    private final Board board;
    private final Goose[] geese;
    private int index;

    private int moves;
    private boolean running = true;

    public Game(Goose[] goose) {
        this.board = new Board(this);
        this.geese = goose;
    }

    public void start() {
        println(Color.PURPLE + "-------------------------------------------------------------" + Color.RESET);
        println();

        while (this.running) {
            final Goose goose = this.geese[this.index];
            print(goose.getColor().toString());

            this.moves++;

            if (goose.isWaiting()) {
                println("Your turn is skipped!");
                goose.tryUnlock();
            } else {
                print("You roll the dice... ");
                sleep(1000);
                final Roll roll = new Roll(this.board, goose);
                println("You rolled a " + roll.getTotal() + " [" + roll.getRoll1() + " & " + roll.getRoll2() + "].");
                sleep(1000);

                final int oldPosition = goose.getPosition();
                roll.execute();
                if (goose.isFinished()) {
                    this.running = false;
                } else {
                    println("You went from [" + oldPosition + "] to [" + goose.getPosition() + "]." + Color.RESET);
                }

                sleep(1000);
            }

            println();
            if (DO_PRINTS)
                this.board.print(this.geese);

            if (++this.index >= this.geese.length)
                this.index = 0;

            if (REQUIRE_ENTER)
                this.scanner.nextLine();
        }
    }

    public long[] getHeatMap() {
        return this.heatmap;
    }

    public Goose[] getGeese() {
        return this.geese;
    }

    public int getMoves() {
        return this.moves;
    }

    private void sleep(int millis) {
        if (!DO_SLEEPS) return;

        try {
            Thread.sleep(millis);
        } catch (InterruptedException exc) {}
    }

    public static void print(String msg) {
        if (DO_PRINTS)
            System.out.print(msg);
    }

    public static void println(String msg) {
        if (DO_PRINTS)
            System.out.println(msg);
    }

    public static void println() {
        if (DO_PRINTS)
            System.out.println();
    }
}

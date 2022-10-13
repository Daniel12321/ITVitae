package nl.itvitae.gog.dice;

import nl.itvitae.gog.game.Board;
import nl.itvitae.gog.game.Goose;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Roll {

    private final int roll1, roll2;

    private final Board board;
    private final Goose goose;

    private boolean backwards;

    public Roll(Board board, Goose goose) {
        this.board = board;
        this.goose = goose;

        final Random r = ThreadLocalRandom.current();
        this.roll1 = r.nextInt(6) + 1;
        this.roll2 = r.nextInt(6) + 1;

        this.backwards = false;
    }

    public void execute() {
        this.goose.move(this);
    }

    public Board getBoard() {
        return board;
    }

    public Goose getGoose() {
        return goose;
    }

    public int getTotal() {
        return this.roll1 + this.roll2;
    }

    public int getRoll1() {
        return this.roll1;
    }

    public int getRoll2() {
        return this.roll2;
    }

    public boolean isBackwards() {
        return backwards;
    }

    public void setBackwards(boolean backwards) {
        this.backwards = backwards;
    }
}

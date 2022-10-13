package nl.itvitae.gog.game;

import nl.itvitae.gog.dice.Roll;

public class Goose {

    private final Color color;
    private int position;
    private boolean waiting;
    private boolean allowUnlock;
    private boolean finished;

    public Goose(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public void setWaiting(boolean value) {
        this.waiting = value;
    }

    public void setAllowUnlock(boolean value) {
        this.allowUnlock = value;
    }

    public void setFinished() {
        this.finished = true;
    }

    public void tryUnlock() {
        if (this.allowUnlock)
            this.waiting = false;
    }

    public void move(Roll roll) {
        this.moveTo(roll.getTotal() + this.position, roll);
    }

    public void moveTo(int position, Roll roll) {
        final Board board = roll.getBoard();
        final int oldPosition = roll.getGoose().getPosition();

        // Move backwards when past finish
        if (position > 63) {

            for (int i = oldPosition + 1; i < 63; i++)
                board.getTile(i).onPass();

            position = 63 - (position - 63);
            roll.setBackwards(true);
        }

        final int minPos = Math.min(oldPosition, position);
        final int maxPos = Math.max(oldPosition, position);
        for (int i = minPos + 1; i < maxPos; i++)
            board.getTile(i).onPass();

        board.getTile(position).arrive(roll);
    }
}

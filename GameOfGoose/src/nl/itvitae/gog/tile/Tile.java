package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;
import nl.itvitae.gog.game.Goose;

public abstract class Tile {

    protected final int index;
    private final String mapText;

    protected Tile(int index, String mapText) {
        this.index = index;
        this.mapText = mapText;
    }

    public void arrive(Roll roll) {
        roll.getBoard().getGame().getHeatMap()[this.index]++;
        this.onArrival(roll);
    }

    protected abstract void onArrival(Roll roll);

    public void onPass() {
    }

    public String getMapText() {
        return this.mapText;
    }

    protected boolean isOccupied(Roll roll) {
        for (Goose goose : roll.getBoard().getGame().getGeese()) {
            if (goose != roll.getGoose() && goose.getPosition() == this.index) {
                Game.println("You landed on a tile that was already occupied. Back to where you came from!");
                return true;
            }
        }

        return false;
    }

    protected boolean isAnyBehind(Roll roll) {
        for (Goose goose : roll.getBoard().getGame().getGeese())
            if (goose.getPosition() < roll.getGoose().getPosition())
                return true;

        return false;
    }
}

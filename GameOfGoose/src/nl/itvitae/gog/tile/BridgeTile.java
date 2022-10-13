package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;

public class BridgeTile extends Tile {

    private final int destination;

    public BridgeTile(int index, int destination) {
        super(index, "TP");

        this.destination = destination;
    }

    public int getDestination() {
        return this.destination;
    }

    @Override
    public void onArrival(Roll roll) {
        Game.println("You hit a bridge tile [" + super.index + "]!");

        roll.getGoose().moveTo(this.destination, roll);
    }
}

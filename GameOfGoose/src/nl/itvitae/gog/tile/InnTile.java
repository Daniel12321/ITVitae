package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;
import nl.itvitae.gog.game.Goose;

public class InnTile extends Tile {

    public InnTile(int index) {
        super(index, "In");
    }

    @Override
    public void onArrival(Roll roll) {
        if (super.isOccupied(roll))
            return;

        Game.println("You arrived at the inn, and sleep the night away.");

        Goose goose = roll.getGoose();
        goose.setWaiting(true);
        goose.setAllowUnlock(true);
        goose.setPosition(super.index);
    }
}

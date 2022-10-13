package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;
import nl.itvitae.gog.game.Goose;

public class PitTile extends Tile {

    private Goose fallen;

    public PitTile(int index) {
        super(index, "Pi");
    }

    @Override
    public void onArrival(Roll roll) {
        if (super.isOccupied(roll))
            return;

        Game.println("You fell in a pit.");

        Goose goose = roll.getGoose();
        goose.setWaiting(true);
        goose.setPosition(super.index);

        if (!super.isAnyBehind(roll)) {
            goose.setAllowUnlock(true);
            return;
        }

        this.fallen = goose;
        goose.setAllowUnlock(false);
    }

    @Override
    public void onPass() {
        if (this.fallen == null)
            return;

        this.fallen.setAllowUnlock(true);
        this.fallen.tryUnlock();
        this.fallen = null;
    }
}

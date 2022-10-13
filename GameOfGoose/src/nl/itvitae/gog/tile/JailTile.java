package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;
import nl.itvitae.gog.game.Goose;

public class JailTile extends Tile {

    private Goose jailed;

    public JailTile(int index) {
        super(index, "Ja");
    }

    @Override
    public void onArrival(Roll roll) {
        Goose goose = roll.getGoose();

        // Goose is only in 'jailed' when someone is behind.
        // That is okey, because they can only land on this tile if they are behind.
        if (this.jailed != null) {
            Game.println("You swapped placed with the goose that was in the jail!");
            this.jailed.setWaiting(false);
            this.jailed.setPosition(goose.getPosition());
            this.jailed = null;
        }

        Game.println("You went to jail!");

        goose.setWaiting(true);
        goose.setPosition(super.index);

        if (!super.isAnyBehind(roll)) {
            goose.setAllowUnlock(true);
            return;
        }

        this.jailed = goose;
        goose.setAllowUnlock(false);
    }

    @Override
    public void onPass() {
        if (this.jailed == null)
            return;

        this.jailed.setAllowUnlock(true);
        this.jailed.tryUnlock();
        this.jailed = null;
    }
}

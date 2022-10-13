package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;

public class GooseTile extends Tile {

    public GooseTile(int index) {
        super(index, "Go");
    }

    public GooseTile(int index, String mapText) {
        super(index, mapText);
    }

    @Override
    public void onArrival(Roll roll) {
        Game.println("You hit a goose tile [" + super.index + "]!");

        int newPos = roll.isBackwards() ? super.index - roll.getTotal() : super.index + roll.getTotal();

        roll.getGoose().moveTo(newPos, roll);
    }
}

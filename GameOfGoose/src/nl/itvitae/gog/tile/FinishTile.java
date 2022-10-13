package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;

public class FinishTile extends Tile {

    public FinishTile(int index) {
        super(index, "Fi");
    }

    @Override
    public void onArrival(Roll roll) {
        Game.println("You hit the finish line! You win.");

        roll.getGoose().setFinished();
        roll.getGoose().setPosition(super.index);
    }
}

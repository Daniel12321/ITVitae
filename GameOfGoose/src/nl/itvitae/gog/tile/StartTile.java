package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;

public class StartTile extends Tile {

    public StartTile(int index) {
        super(index, "St");
    }

    @Override
    public void onArrival(Roll roll) {
        roll.getGoose().setPosition(super.index);
    }
}

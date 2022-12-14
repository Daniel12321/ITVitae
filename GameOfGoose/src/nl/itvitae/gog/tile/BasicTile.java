package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;

public class BasicTile extends Tile {

    public BasicTile(int index) {
        super(index, (index < 10 ? " " : "") + index);
    }

    @Override
    public void onArrival(Roll roll) {
        if (super.isOccupied(roll))
            return;

        roll.getGoose().setPosition(super.index);
    }
}

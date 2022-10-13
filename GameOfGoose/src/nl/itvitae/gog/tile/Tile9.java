package nl.itvitae.gog.tile;

import nl.itvitae.gog.dice.Roll;
import nl.itvitae.gog.game.Game;

public class Tile9 extends GooseTile {

    public Tile9(int index) {
        super(index, Integer.toString(index));
    }

    @Override
    public void onArrival(Roll roll) {
        if (roll.getTotal() != 9) {
            super.onArrival(roll);
            return;
        }

        Game.println("You hit tile9 from [0]!");

        int newPos = roll.getRoll1() == 6 || roll.getRoll2() == 6 ? 26 : 53;
        roll.getGoose().moveTo(newPos, roll);
    }
}

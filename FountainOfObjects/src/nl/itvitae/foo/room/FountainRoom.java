package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Map;
import nl.itvitae.foo.game.Player;

public class FountainRoom extends IRoom {

    private final Map map;

    public FountainRoom(Map map) {
        super('F');
        this.map = map;
    }

    @Override
    public void onInside(Player player) {
        if (this.map.isFountainActive()) {
            System.out.println("You hear the rushing waters from the Fountain of Objects. It has been reactivated!");
        } else {
            System.out.println("You hear water dripping in this room. The Fountain of Objects is here!");
        }
    }

    @Override
    public void enableFountain() {
        if (this.map.isFountainActive()) {
            System.out.println("The fountain is already enabled.");
        } else {
            this.map.setFountainActive();
        }
    }
}

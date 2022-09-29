package nl.itvitae.foo.room;

import nl.itvitae.foo.game.FountainGame;
import nl.itvitae.foo.game.Player;

public class EntranceRoom extends IRoom {

    private final FountainGame game;

    public EntranceRoom(FountainGame game) {
        super('^');
        this.game = game;
    }

    @Override
    public void onInside(Player player) {
        if (this.game.getMap().isFountainActive()) {
            this.game.victory();
        } else {
            System.out.println("You see light coming from the cavern entrance.");
        }
    }
}

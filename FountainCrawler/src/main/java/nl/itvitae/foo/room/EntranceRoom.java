package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Game;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.util.LineType;

import java.util.List;

public class EntranceRoom extends Room {

    private final Game game;

    public EntranceRoom(Game game) {
        super("", '\u0394');

        this.game = game;
    }

    @Override
    public List<String> handleInside(Player player) {
        if (this.game.getWorld().isFountainActive()) {
            this.game.victory();
            return List.of();
        } else {
            return List.of(LineType.ROOM.makeLine("You see light coming from the cavern entrance."));
        }
    }
}

package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.util.LineType;

import java.util.List;

public class EmptyRoom extends Room {

    public EmptyRoom() {
        super("", ' ');

        super.setDiscovered();
    }

    @Override
    public List<String> handleInside(Player player) {
        return List.of(LineType.ROOM.makeLine("This room is empty."));
    }
}

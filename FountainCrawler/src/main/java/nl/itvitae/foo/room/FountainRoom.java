package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;
import nl.itvitae.foo.util.LineType;

import java.util.List;

public class FountainRoom extends Room {

    private final World world;

    public FountainRoom(World world) {
        super("You hear a faint sound of dripping water...", 'F');

        this.world = world;
    }

    @Override
    public List<String> handleInside(Player player) {
        if (this.world.isFountainActive()) {
            return List.of(LineType.ROOM.makeLine("You hear the rushing waters from the Fountain of Objects. It has been reactivated!"));
        } else {
            return List.of(LineType.ROOM.makeLine("You hear water dripping in this room. The Fountain of Objects is here!"));
        }
    }
}

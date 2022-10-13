package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.exception.InvalidDirectionException;
import nl.itvitae.foo.game.Game;
import nl.itvitae.foo.game.Location;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;
import nl.itvitae.foo.room.MonsterRoom;
import nl.itvitae.foo.util.LineType;

public class ThrowCommand extends Command {

    private final Game game;

    public ThrowCommand(Game game) {
        super("throw", 3);

        this.game = game;
    }

    @Override
    public void execute(Player player, World world, String[] args) throws InvalidCommandException {
        Location l = player.getLocation().clone();

        if (!l.move(args[2]))
            throw new InvalidDirectionException();

        player.removeBomb();

        if (!(world.getRoom(l) instanceof MonsterRoom room))
            throw new InvalidCommandException(LineType.WARNING.makeLine("There was a big explosion, but nothing was hurt..."));

        room.getMonster().damage(999);
        room.setDiscovered();
        room.setDead();
        this.game.rerender();
    }
}

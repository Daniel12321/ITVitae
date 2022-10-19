package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.game.Game;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;
import nl.itvitae.foo.room.FountainRoom;
import nl.itvitae.foo.util.LineType;

public class FountainCommand extends Command {

    private final Game game;

    public FountainCommand(Game game) {
        super("enable", 2);

        this.game = game;
    }

    @Override
    public void execute(Player player, World world, String[] args) throws InvalidCommandException {
        if (!args[1].equalsIgnoreCase("fountain"))
            throw new InvalidCommandException();

        if (!(world.getRoom(player) instanceof FountainRoom))
            throw new InvalidCommandException(LineType.WARNING.makeLine("Nothing happened."));

        if (world.isFountainActive())
            throw new InvalidCommandException(LineType.WARNING.makeLine("The fountain is already enabled."));

        world.setFountainActive();
        this.game.rerender();
    }
}

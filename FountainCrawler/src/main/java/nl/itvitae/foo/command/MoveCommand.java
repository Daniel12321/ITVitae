package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.exception.InvalidDirectionException;
import nl.itvitae.foo.game.Game;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;

public class MoveCommand extends Command {

    private final Game game;

    public MoveCommand(Game game) {
        super("move", 2);

        this.game = game;
    }

    @Override
    public void execute(Player player, World world, String[] args) throws InvalidCommandException {
        if (!player.move(args[1]))
            throw new InvalidDirectionException();

        this.game.rerender();
    }
}

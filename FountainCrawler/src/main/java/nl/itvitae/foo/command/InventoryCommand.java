package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;

public class InventoryCommand extends Command {

    public InventoryCommand() {
        super("open", 2);
    }

    @Override
    public void execute(Player player, World world, String[] args) throws InvalidCommandException {
        throw new InvalidCommandException("Unimplemented");
    }
}

package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;

public abstract class Command {

    private final String name;
    private final int args;

    public Command(String name, int args) {
        this.name = name;
        this.args = args;
    }

    public abstract void execute(Player player, World world, String[] args) throws InvalidCommandException;

    public boolean matches(int argCount) {
        return this.args == argCount;
    }
}

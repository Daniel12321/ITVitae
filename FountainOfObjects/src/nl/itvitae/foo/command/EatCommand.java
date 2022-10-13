package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;
import nl.itvitae.foo.item.AppleItem;
import nl.itvitae.foo.util.LineType;

public class EatCommand extends Command {

    public EatCommand() {
        super("eat", 2);
    }

    @Override
    public void execute(Player player, World world, String[] args) throws InvalidCommandException {
        if (!args[1].equalsIgnoreCase("apple"))
            throw new InvalidCommandException(LineType.ERROR.makeLine("Invalid food item!"));

        if (player.getHp() == player.getMaxHp())
            throw new InvalidCommandException(LineType.WARNING.makeLine("You are already at max HP."));

        player.removeApple();
        player.heal(AppleItem.HP);

        LineType.REGEN.print("You ate an apple, and regained " + AppleItem.HP + " HP!");
        LineType.REGEN.print("Your HP is now " + player.getHp() + " / " + player.getMaxHp() + ".");
    }
}

package nl.itvitae.foo.command;

import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.game.World;
import nl.itvitae.foo.item.Item;
import nl.itvitae.foo.room.ItemRoom;
import nl.itvitae.foo.util.LineType;

public class PickupCommand extends Command {

    public PickupCommand() {
        super("pickup", 2);
    }

    @Override
    public void execute(Player player, World world, String[] args) throws InvalidCommandException {
        if (!(world.getRoom(player) instanceof ItemRoom room))
            throw new InvalidCommandException(LineType.WARNING.makeLine("There is no item in this room."));

        Item item = room.getItem();
        if (item == null)
            throw new InvalidCommandException(LineType.WARNING.makeLine("The item that was here is long gone."));

        if (item.isHand()) {
            Item old = player.setHandItem(item);
            room.setItem(old);
            if (old == null) {
                LineType.ROOM.print("You picked up the " + item.getName() + ".");
            } else {
                LineType.ROOM.print("You swapped the " + old.getName() + " in your hand with this " + item.getName() + ".");
            }
        } else {
            if (!player.addItem(item))
                throw new InvalidCommandException(LineType.WARNING.makeLine("Your pockets are overflowing..."));

            room.setItem(null);
            LineType.ROOM.print("You picked up the " + item.getName() + ", and put it in your pocket.");
        }
    }
}

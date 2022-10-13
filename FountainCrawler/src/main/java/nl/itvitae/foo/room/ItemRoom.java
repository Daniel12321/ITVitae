package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.item.Item;
import nl.itvitae.foo.util.LineType;

import java.util.List;

public class ItemRoom extends Room {

    private Item item;

    public ItemRoom(Item item) {
        super("", '\u0398');

        this.item = item;
    }

    @Override
    public List<String> handleInside(Player player) {
        if (this.item == null) {
            return List.of(LineType.ROOM.makeLine("There used to be something here... But now its empty."));
        } else {
            return List.of(LineType.ROOM.makeLine("You found an item laying on the ground. Its a(n) " + this.item.getName()));
        }
    }

    @Override
    public String getHint() {
        return this.item == null ? "" : "I sense an item nearby.";
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

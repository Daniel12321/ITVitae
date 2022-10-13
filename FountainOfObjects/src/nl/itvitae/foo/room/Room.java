package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Game;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.item.Item;
import nl.itvitae.foo.monster.MonsterType;
import nl.itvitae.foo.util.Color;

import java.util.List;
import java.util.Random;

public abstract class Room {

    private final String hint;
    private final char mapChar;
    private Color mapCharColor;
    private boolean discovered;

    protected Room(String hint, char mapChar) {
        this(hint, mapChar, Color.WHITE);
    }

    protected Room(String hint, char mapChar, Color color) {
        this.hint = hint;
        this.mapChar = mapChar;
        this.mapCharColor = color;
        this.discovered = !Game.HIDE_CHARS;
    }

    public void setMapCharColor(Color color) {
        this.mapCharColor = color;
    }

    public void setDiscovered() {
        this.discovered = true;
    }

    public String getHint() {
        return this.hint;
    }

    public String getMapChar() {
        return this.discovered ? this.mapCharColor.toString() + this.mapChar + Color.RESET : "?";

//        return Game.HIDE_CHARS ? this.discovered || this instanceof EmptyRoom ? this.mapChar : '?' : this.mapChar;
    }

    /**
     * Handles all room logic
     *
     * @param player The {@link Player} object
     * @return The response line
     */
    public abstract List<String> handleInside(Player player);

    public static Room random(Game game, Random r) {
        double sr = r.nextDouble();
        if (sr > 0.7) {
            return new MonsterRoom(game, MonsterType.random(r.nextDouble()));
        } else if (sr > 0.6) {
            return new ItemRoom(Item.random(r.nextDouble()));
        } else if (sr < 0.03) {
            return new FountainRoom(game.getWorld());
        } else {
            return new EmptyRoom();
        }
    }
}

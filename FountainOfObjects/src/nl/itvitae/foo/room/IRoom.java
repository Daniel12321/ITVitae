package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Player;

public abstract class IRoom {

    private final char mapChar;

    protected IRoom(char mapChar) {
        this.mapChar = mapChar;
    }

    public abstract void onInside(Player player);

    public void enableFountain() {
        System.out.println("Nothing happened.");
    }

    public char getMapChar() {
        return this.mapChar;
    }
}

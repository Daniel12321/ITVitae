package nl.itvitae.foo.game;

import nl.itvitae.foo.room.*;

import java.util.Optional;
import java.util.Random;

public class Map {

    private static final Random R = new Random();
    private final IRoom[][] rooms;

    private boolean fountainActive = false;

    public Map(FountainGame game, int sizeX, int sizeY, int monsters) {
        this.rooms = new IRoom[sizeX][sizeY];
        this.rooms[0][0] = new EntranceRoom(game);

        this.setRoom(this.getNextUnusedLocation(), new FountainRoom(this));
        this.setRoom(this.getNextUnusedLocation(), new AppleRoom());

        for (int i = 0; i < monsters; i++)
            this.setRoom(this.getNextUnusedLocation(), new MonsterRoom(game));
    }

    public IRoom getRoom(Player player) {
        return this.rooms[player.getX()][player.getY()];
    }

    public IRoom getRoom(Location loc) {
        return this.rooms[loc.getX()][loc.getY()];
    }

    public boolean isFountainActive() {
        return this.fountainActive;
    }

    public void setFountainActive() {
        this.fountainActive = true;
    }

    public int getWidth() {
        return this.rooms.length;
    }

    public int getHeight() {
        return this.rooms[0].length;
    }

    public void print(Player player) {
        for (int y = this.rooms[0].length - 1; y >= 0; y--) {
            System.out.print(y + "    ");
            for (int x = 0; x < this.rooms.length; x++) {
                if (x == player.getX() && y == player.getY()) {
                    System.out.print("X ");
                } else {
                    System.out.print(Optional.ofNullable(this.rooms[x][y]).map(IRoom::getMapChar).orElse(' ') + " ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("     ");

        for (int x = 0; x < this.rooms.length; x++)
            System.out.print(x + " ");
        System.out.println();
    }

    private void setRoom(Location loc, IRoom room) {
        this.rooms[loc.getX()][loc.getY()] = room;
    }

    private Location getNextUnusedLocation() {
        Location l;

        do {
            l = new Location(R.nextInt(this.rooms.length), R.nextInt(this.rooms[0].length));
        } while (this.getRoom(l) != null);

        return l;
    }
}

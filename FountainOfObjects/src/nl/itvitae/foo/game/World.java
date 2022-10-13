package nl.itvitae.foo.game;

import nl.itvitae.foo.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class World {

    private final long seed;
    private final Game game;
    private final List<Chunk> chunks;

    private boolean fountainActive;

    public World(long seed, Game game) {
        this.seed = seed;
        this.game = game;
        this.chunks = new ArrayList<>();
    }

    public Room getRoom(int x, int y) {
        return this.getChunk(x, y).getRoom(x, y);
    }

    public Room getRoom(Player player) {
        return this.getRoom(player.getLocation());
    }

    public Room getRoom(Location loc) {
        return this.getRoom(loc.getX(), loc.getY());
    }

    private Chunk getChunk(int x, int y) {
        int chunkX = x < 0 ? (x + 1) / 8 - 1 : x / 8;
        int chunkY = y < 0 ? (y + 1) / 8 - 1 : y / 8;

        Chunk chunk = this.chunks.stream().filter(c -> c.getChunkX() == chunkX && c.getChunkY() == chunkY).findFirst().orElse(null);
        if (chunk == null) {
            chunk = new Chunk(game, this.seed, chunkX, chunkY);
            this.chunks.add(chunk);
        }

        return chunk;
    }

    public boolean isFountainActive() {
        return this.fountainActive;
    }

    public void setFountainActive() {
        this.fountainActive = true;
    }

    /**
     * Prints the map in proximity to the player.
     *
     * @param player The {@link Player} object
     * @param visibility The radius of visibility
     */
    public void print(Player player, int visibility, Queue<String> lines) {
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < visibility * 4 + 5; i++)
            bar.append('█');

        System.out.print(bar);
        if (!lines.isEmpty())
            System.out.print(" " + lines.poll());
        System.out.println();

        Location loc = player.getLocation();
        for (int ry = visibility; ry >= -visibility; ry--) {
            System.out.print("█ ");
            for (int rx = -visibility; rx <= visibility; rx++) {
                int x = loc.getX() + rx, y = loc.getY() + ry;

                if (rx == 0 && ry == 0) {
                    System.out.print("\u03C7 ");
                } else {
                    System.out.print(this.getRoom(x, y).getMapChar() + " ");
                }
            }
            System.out.print("█ ");

            if (!lines.isEmpty())
                System.out.print(lines.poll());
            System.out.println();
        }

        System.out.print(bar);
        if (!lines.isEmpty())
            System.out.print(lines.poll());
        System.out.println();

        while (!lines.isEmpty())
            System.out.println(lines.poll());
    }

    public Room[] getAdjacentRooms(Player player) {
        Location loc = player.getLocation();
        int x = loc.getX(), y = loc.getY();
        return new Room[]{
                this.getRoom(x, y + 1),
                this.getRoom(x, y - 1),
                this.getRoom(x + 1, y),
                this.getRoom(x - 1, y)
        };
    }
}

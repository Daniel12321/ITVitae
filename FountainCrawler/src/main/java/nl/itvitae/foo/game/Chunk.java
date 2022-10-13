package nl.itvitae.foo.game;

import nl.itvitae.foo.room.EntranceRoom;
import nl.itvitae.foo.room.Room;

import java.util.Random;

public class Chunk {

    private final int chunkX, chunkY;
    private final Room[][] rooms;

    public Chunk(Game game, long seed, int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.rooms = new Room[8][8];

        this.populate(game, seed);
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public Room getRoom(int x, int y) {
        int relX = x - (this.chunkX * 8);
        int relY = y - (this.chunkY * 8);

        return this.rooms[relX][relY];
    }

    private void populate(Game game, long seed) {
        Random r = new Random(seed + this.chunkX + (this.chunkY * 10_000L));

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                this.rooms[x][y] = Room.random(game, r);

        if (this.chunkX == 0 && this.chunkY == 0)
            this.rooms[0][0] = new EntranceRoom(game);
    }
}

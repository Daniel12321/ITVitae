package nl.itvitae.gog.game;

import nl.itvitae.gog.tile.*;

public class Board {

    private static final int[][] MAP = {
            { 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20},
            { 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19},
            { 34, -1, 60, 59, 58, 57, 56, 55, 54, 53, 52, -1, 18},
            { 35, -1, 61, -1, -1, -1, -1, -1, -1, -1, 51, -1, 17},
            { 36, -1, 62, 63, -1, -1, -1, -1, -1, -1, 50, -1, 16},
            { 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, 15},
            { 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, -1, 14},
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13},
            {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12}
    };

    private static final int[] GOOSE_TILES = { 5, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59 };

    private final Game game;
    private final Tile[] tiles;

    public Board(Game game) {
        this.game = game;

        tiles = new Tile[64];
        tiles[0] = new StartTile(0);
        tiles[1] = null;
        tiles[6] = new BridgeTile(6, 12);
        tiles[9] = new Tile9(9);
        tiles[19] = new InnTile(19);
        tiles[31] = new PitTile(31);
        tiles[42] = new BridgeTile(42, 37);
        tiles[52] = new JailTile(52);
        tiles[58] = new BridgeTile(58, 0);
        tiles[63] = new FinishTile(63);

        for (int i : GOOSE_TILES)
            tiles[i] = new GooseTile(i);

        for (int i = 0; i < 64; i++)
            if (tiles[i] == null)
                tiles[i] = new BasicTile(i);
    }

    public Game getGame() {
        return this.game;
    }

    public Tile getTile(int index) {
        return this.tiles[index];
    }

    public void print(Goose[] geese) {
        System.out.println(Color.PURPLE + "-------------------------------------------------------------" + Color.RESET);

        for (int x = 0; x < MAP.length; x++) {
            for (int y = 0; y < MAP[0].length; y++) {
                int pos = MAP[x][y];
                if (pos == -1) {
                    System.out.print("    ");
                    continue;
                }
                final Goose goose = this.getGoose(geese, pos);
                System.out.print('[' + (goose != null ? goose.getColor() + "||" + Color.RESET : tiles[pos].getMapText()) + ']');
            }
            System.out.println();
        }

        System.out.println(Color.PURPLE + "-------------------------------------------------------------" + Color.RESET);
    }

    private Goose getGoose(Goose[] geese, int position) {
        for (Goose goose : geese)
            if (goose.getPosition() == position)
                return goose;

        return null;
    }
}

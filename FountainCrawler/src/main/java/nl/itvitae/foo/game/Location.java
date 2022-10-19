package nl.itvitae.foo.game;

public class Location {

    private int x, y;

    public Location() {
        this(0, 0);
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Change the location's x & y according to the compass direction
     *
     * @param direction The compass direction
     * @return whether the move was successful
     */
    public boolean move(String direction) {
        switch (direction.toLowerCase()) {
            case "n", "north" -> this.y++;
            case "e", "east" -> this.x++;
            case "s", "south" -> this.y--;
            case "w", "west" -> this.x--;
            default -> {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "[" + x + ',' + y + ']';
    }

    @Override
    public Location clone() {
        return new Location(this.x, this.y);
    }
}

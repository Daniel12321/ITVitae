package nl.itvitae.foo.game;

public class Player {

    private final int maxLives;

    private int lives;
    private int x, y;

    public Player(int lives) {
        this.maxLives = lives;
        this.lives = lives;
        this.x = 0;
        this.y = 0;
    }

    public boolean move(Map map, String direction) {
        int newX = x, newY = y;
        switch (direction.toLowerCase()) {
            case "north" -> newY++;
            case "east" -> newX++;
            case "south" -> newY--;
            case "west" -> newX--;
            default -> {
                System.out.println("Invalid direction!");
                return false;
            }
        }

        if (newX < 0 || newX >= map.getWidth() || newY < 0 || newY >= map.getHeight()) {
            System.out.println("You hit a wall.");
            return false;
        }

        this.x = newX;
        this.y = newY;
        return true;
    }

    public int getMaxLives() {
        return this.maxLives;
    }

    public int getLives() {
        return this.lives;
    }

    public void addLive() {
        this.lives++;
    }

    public void removeLife() {
        this.lives--;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

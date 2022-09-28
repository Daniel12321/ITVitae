package nl.itvitae.foo;

import java.util.Scanner;

public class Game {

    private static final int ENTRANCE_ROOM = 1;
    private static final int FOUNTAIN_ROOM = 2;

    private static final int[][] MAP = new int[][] {
            {ENTRANCE_ROOM, 0, FOUNTAIN_ROOM, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    private final Scanner scanner;

    private int x = 0, y = 0;
    private boolean hasWon = false;
    private boolean fountainEnabled = false;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            this.printLocation();
            this.printRoomInfo();

            if (this.hasWon)
                break;

            System.out.print("What do you want to do: ");
            this.handleCommand(this.scanner.nextLine());
            System.out.println("------------------------------------------------------------");
        }

        System.out.println("You win!");
    }

    private void printLocation() {
        System.out.println("You are in the room at [" + x + "," + y + "]");
    }

    private void printRoomInfo() {
        int roomType = MAP[x][y];
        if (roomType == FOUNTAIN_ROOM) {
            if (this.fountainEnabled) {
                System.out.println("You hear the rushing waters from the Fountain of Objects. It has been reactivated!");
            } else {
                System.out.println("You hear water dripping in this room. The Fountain of Objects is here!");
            }
        } else if (roomType == ENTRANCE_ROOM) {
            if (this.fountainEnabled) {
                System.out.println("The Fountain of Objects has been reactivated, and you have escaped with your life!");
                this.hasWon = true;
            } else {
                System.out.println("You see light coming from the cavern entrance.");
            }
        }
    }

    private void handleCommand(String command) {
        String[] args = command.split("\s");

        if (args.length < 2) {
            System.out.println("Invalid command!");
            return;
        }

        if (args[0].equalsIgnoreCase("move")) {
            this.move(args[1]);
        } else if (args[0].equalsIgnoreCase("enable") && args[1].equalsIgnoreCase("fountain")) {
            this.enableFountain();
        } else {
            System.out.println("Invalid command!");
        }
    }

    private void move(String direction) {
        int newX = x, newY = y;
        switch (direction) {
            case "north": newX++; break;
            case "east": newY++; break;
            case "south": newX--; break;
            case "west": newY--; break;
            default:
                System.out.println("Invalid direction!");
                return;
        }

        if (newX < 0 || newX > 3 || newY < 0 || newY > 3) {
            System.out.println("You hit a wall.");
            return;
        }

        this.x = newX;
        this.y = newY;
    }

    private void enableFountain() {
        if (MAP[x][y] != FOUNTAIN_ROOM) {
            System.out.println("Nothing happened.");
            return;
        }

        if (this.fountainEnabled) {
            System.out.println("The fountain is already enabled.");
        } else {
            this.fountainEnabled = true;
        }
    }
}
